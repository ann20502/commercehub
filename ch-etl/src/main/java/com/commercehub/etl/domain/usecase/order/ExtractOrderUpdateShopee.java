package com.commercehub.etl.domain.usecase.order;

import com.commercehub.common.TimeZoneUtils;
import com.commercehub.common.TimedTaskUtils;
import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.order.Order;
import com.commercehub.etl.domain.entity.order.OrderTransformer;
import com.commercehub.etl.domain.entity.schduler.TimedTask;
import com.commercehub.etl.domain.exception.ETLRuntimeException;
import com.commercehub.etl.domain.repository.LinkingRepository;
import com.commercehub.etl.domain.repository.OrderRepository;
import com.commercehub.etl.domain.repository.TimedTaskRepository;
import com.commercehub.rest.shopee.ShopeeOrderService;
import com.commercehub.rest.shopee.ShopeePaymentService;
import com.commercehub.rest.shopee.input.GetEscrowDetailInput;
import com.commercehub.rest.shopee.input.GetOrderDetailInput;
import com.commercehub.rest.shopee.input.GetOrderListInput;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.GetEscrowDetailOutput;
import com.commercehub.rest.shopee.output.GetOrderDetailOutput;
import com.commercehub.rest.shopee.output.GetOrderListOutput;
import com.google.cloud.Tuple;
import io.reactivex.Observable;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.converters.multi.MultiRxConverters;
import io.smallrye.mutiny.converters.uni.UniRxConverters;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class ExtractOrderUpdateShopee {

    @Inject
    Logger log;

    @Inject
    TimedTaskRepository taskRepository;

    @Inject
    LinkingRepository linkingRepository;

    @Inject
    OrderRepository orderRepository;

    @Inject
    @RestClient
    ShopeeOrderService orderService;

    @Inject
    @RestClient
    ShopeePaymentService paymentService;

    private String collectionName() {
        return TimedTaskUtils.COLLECTION_PREFIX + "ShopeeOrderUpdate";
    }

    private boolean shallRun(Tuple<TimedTask,Linking> taskAndLink) {
        return true;
    }

    /**
     * Save order updates with UTC timezone
     *
     * @param documentId task ID to be processed
     * @return return the list of order updates extracted
     */
    public Uni<List<Order>> extract(String documentId) {
        Uni<Tuple<TimedTask,Linking>> taskAndLinkingStream = getTaskAndLinking(documentId).memoize().indefinitely();

        return taskAndLinkingStream
                .toMulti().filter(this::shallRun)
                .flatMap(this::getOrderList)
                .flatMap(orderListOutput -> getOrderDetail(orderListOutput, taskAndLinkingStream))
                .flatMap(orderDetailOutput -> getOrderDetailWithEscrow(orderDetailOutput, taskAndLinkingStream))
                .collect().asList()
                .onItem().invoke(() -> updateTask(documentId, TimedTask.STATUS_COMPLETED))
                .flatMap(orders -> saveOrder(orders, taskAndLinkingStream))
                .onFailure().invoke(
                    error -> {
                        log.error("Error while extracting order update: " + error.getMessage());
                        boolean result = updateTask(documentId, TimedTask.STATUS_ERROR);
                        log.info("Attempt to set task status to [" + TimedTask.STATUS_ERROR + "]: " + result);
                        throw new RuntimeException(error);
                    }
                );
    }

    private Uni<Tuple<TimedTask, Linking>> getTaskAndLinking(String documentId) {
        return Uni.createFrom().item(documentId)
                .map(input -> taskRepository.get(collectionName(), input))
                .map(task -> Tuple.of(
                        task,
                        linkingRepository.get(task.getPlatform(), task.getShopId(), true, true)
                ));
    }

    private Multi<GetOrderListOutput> getOrderList(Tuple<TimedTask,Linking> taskAndLinking) {
        return Multi.createFrom().converter(MultiRxConverters.fromObservable(), getOrderListRx(taskAndLinking));
    }

    private Observable<GetOrderListOutput> getOrderListRx(Tuple<TimedTask,Linking> taskAndLinking) {
        return Observable.fromIterable(getIndex(10, 10))
                .concatMap(index -> {
                    TimedTask task = taskAndLinking.x();
                    Linking linking = taskAndLinking.y();

                    final int partnerId = Integer.parseInt(linking.getPartnerId());
                    final int shopId = Integer.parseInt(linking.getShopId());
                    final ShopApiCommonParam commonParam = new ShopApiCommonParam(
                            partnerId, linking.getPartnerSecret(),
                            linking.getAccessToken(), shopId
                    );

                    final String finalCursor = index == 0 ? "" : index.toString();
                    final long timeFrom = task.getParamTimeFrom().getTime() / 1000L;
                    final long timeTo = task.getParamTimeTo().getTime() / 1000L;
                    final int pageSize = 10;
                    final GetOrderListInput apiInput = new GetOrderListInput(
                            GetOrderListInput.TimeRangeField.CREATE_TIME,
                            timeFrom, timeTo, pageSize, finalCursor
                    );

                    return orderService.getOrderList(commonParam, apiInput)
                            .convert().with(UniRxConverters.toObservable());
                })
                .filter(output -> {
                    log.info("Order list output: " + output);
                    if ( output.getError() != null && output.getError().length() > 0 ) {
                        log.error("Error while extracting order list: " + output.getMessage());
                        log.error(output);
                        throw new RuntimeException("Error while extracting order list: " + output.getMessage());
                    }
                    return true;
                })
                .takeUntil(output -> !output.getResponse().isMore());
    }

    private List<Integer> getIndex(int pageSize, int noOfOccurrence) {
        List<Integer> result = new ArrayList<>();
        int cursor = 0;
        for ( int i = 0; i < noOfOccurrence; i++ ) {
            result.add(cursor);
            cursor += pageSize;
        }
        return result;
    }

    private Multi<GetOrderDetailOutput> getOrderDetail(GetOrderListOutput apiOutput, Uni<Tuple<TimedTask,Linking>> taskAndLinkingStream) {
        return Multi.createFrom().iterable(apiOutput.getResponse().getOrder_list())
                .map(GetOrderListOutput.Order::getOrder_sn)
                .group().intoLists().of(50)
                .flatMap(orderSnList -> taskAndLinkingStream.flatMap(
                        taskAndLinking -> {
                            Linking linking = taskAndLinking.y();
                            final int partnerId = Integer.parseInt(linking.getPartnerId());
                            final int shopId = Integer.parseInt(linking.getShopId());

                            final ShopApiCommonParam commonParam = new ShopApiCommonParam(
                                    partnerId, linking.getPartnerSecret(),
                                    linking.getAccessToken(), shopId
                            );

                            String orderSn = String.join(",", orderSnList);
                            GetOrderDetailInput apiInput = new GetOrderDetailInput(orderSn);
                            return orderService.getOrderDetail(commonParam, apiInput);
                        }).toMulti()
                )
                .filter(output -> {
                    log.info("Order Detail output: " + output);
                    if ( output.getError() != null && output.getError().length() > 0 ) {
                        log.error("Error while extracting order detail: " + output.getMessage());
                        throw new RuntimeException("Error while extracting order detail: " + output.getMessage());
                    }
                    return true;
                });
    }

    private Multi<Order> getOrderDetailWithEscrow(GetOrderDetailOutput apiOutput, Uni<Tuple<TimedTask,Linking>> taskAndLinkingStream) {
        return Multi.createFrom().iterable(apiOutput.getResponse().getOrder_list())
                .flatMap(output ->
                        getOrderEscrow(output.getOrder_sn(), taskAndLinkingStream)
                        .map(escrow -> OrderTransformer.from(output, escrow))
                        .toMulti()
                );
    }

    private Uni<GetEscrowDetailOutput> getOrderEscrow(String orderSn, Uni<Tuple<TimedTask,Linking>> taskAndLinkingStream) {
        return taskAndLinkingStream.flatMap(
                taskAndLinking -> {
                    Linking linking = taskAndLinking.y();
                    final int partnerId = Integer.parseInt(linking.getPartnerId());
                    final int shopId = Integer.parseInt(linking.getShopId());

                    final ShopApiCommonParam commonParam = new ShopApiCommonParam(
                            partnerId, linking.getPartnerSecret(),
                            linking.getAccessToken(), shopId
                    );

                    GetEscrowDetailInput input = new GetEscrowDetailInput(orderSn);
                    return paymentService.getEscrowDetail(commonParam, input);
                }
        );
    }

    private boolean updateTask(String documentId, String status) {
        boolean result = taskRepository.updateToEnd(
                collectionName(), documentId, status,
                TimeZoneUtils.getDate(Instant.now().toEpochMilli())
        );
        if ( !result ) { throw new RuntimeException("Failed to update task status"); }
        return result;
    }

    private Uni<List<Order>> saveOrder(List<Order> orders, Uni<Tuple<TimedTask,Linking>> taskAndLinkingStream) {
        if (orders.isEmpty()) { return Uni.createFrom().item(orders); }
        return taskAndLinkingStream.flatMap(
                taskAndLinking -> {
                    Linking linking = taskAndLinking.y();
                    return orderRepository
                            .add(linking.getPlatform(), linking.getShopId(), orders)
                            .map(result -> {
                                if (!result) { throw new ETLRuntimeException("Failed to save order update"); }
                                return orders;
                            });
                }
        );
    }

}
