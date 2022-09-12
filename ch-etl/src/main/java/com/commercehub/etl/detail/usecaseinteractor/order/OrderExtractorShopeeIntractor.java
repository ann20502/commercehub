package com.commercehub.etl.detail.usecaseinteractor.order;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.order.OrderShopee;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.usecase.order.OrderExtractorShopee;
import com.commercehub.etl.detail.usecaseinteractor.order.transformer.OrderShopeeTransformer;
import com.commercehub.rest.shopee.ShopeeOrderService;
import com.commercehub.rest.shopee.ShopeePaymentService;
import com.commercehub.rest.shopee.input.GetEscrowDetailInput;
import com.commercehub.rest.shopee.input.GetOrderDetailInput;
import com.commercehub.rest.shopee.input.GetOrderListInput;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.GetEscrowDetailOutput;
import com.commercehub.rest.shopee.output.GetOrderDetailOutput;
import com.commercehub.rest.shopee.output.GetOrderListOutput;
import io.reactivex.Observable;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.converters.multi.MultiRxConverters;
import io.smallrye.mutiny.converters.uni.UniRxConverters;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class OrderExtractorShopeeIntractor implements OrderExtractorShopee {

    @Inject
    Logger log;

    @Inject
    @RestClient
    ShopeeOrderService orderService;

    @Inject
    @RestClient
    ShopeePaymentService paymentService;

    @Override
    public Uni<List<OrderShopee>> extract(Linking linking, TimedTask task, ExtractionType extractionType) {
        return getOrderList(linking, task, extractionType)
                .flatMap(orderListOutput -> getOrderDetail(linking, orderListOutput))
                .flatMap(orderDetailOutput -> getOrderDetailWithEscrow(linking, orderDetailOutput))
                .collect().asList();
    }

    private Multi<GetOrderListOutput> getOrderList(Linking linking, TimedTask task, ExtractionType extractionType) {
        return Multi.createFrom().converter(
                MultiRxConverters.fromObservable(),
                getOrderListRx(linking, task, extractionType)
        );
    }

    private Observable<GetOrderListOutput> getOrderListRx(Linking linking, TimedTask task, ExtractionType extractionType) {
        return Observable.fromIterable(getIndex(10, 10))
                .concatMap(index -> {
                    final int partnerId = Integer.parseInt(linking.partnerId);
                    final int shopId = Integer.parseInt(linking.shopId);
                    final ShopApiCommonParam commonParam = new ShopApiCommonParam(
                            partnerId, linking.partnerSecret,
                            linking.accessToken, shopId
                    );

                    final String finalCursor = index == 0 ? "" : index.toString();
                    final long timeFrom = task.paramTimeFrom.getEpochSecond();
                    final long timeTo = task.paramTimeTo.getEpochSecond();
                    final int pageSize = 10;
                    final GetOrderListInput apiInput = new GetOrderListInput(
                            getTimeRangeField(extractionType),
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

    private GetOrderListInput.TimeRangeField getTimeRangeField(ExtractionType extractionType) {
        switch(extractionType) {
            case UPDATE:
                return GetOrderListInput.TimeRangeField.UPDATE_TIME;

            case NEW:
            default:
                return GetOrderListInput.TimeRangeField.CREATE_TIME;
        }
    }

    private Multi<GetOrderDetailOutput> getOrderDetail(Linking linking, GetOrderListOutput orderListOutput) {
        return Multi.createFrom()
                .iterable(orderListOutput.getResponse().getOrder_list())
                .map(GetOrderListOutput.Order::getOrder_sn)
                .group().intoLists().of(50)
                .flatMap(orderSnList -> {
                    final int partnerId = Integer.parseInt(linking.partnerId);
                    final int shopId = Integer.parseInt(linking.shopId);

                    final ShopApiCommonParam commonParam = new ShopApiCommonParam(
                            partnerId, linking.partnerSecret,
                            linking.accessToken, shopId
                    );

                    String orderSn = String.join(",", orderSnList);
                    GetOrderDetailInput apiInput = new GetOrderDetailInput(orderSn);
                    return orderService.getOrderDetail(commonParam, apiInput).toMulti();
                })
                .filter(orderDetailOutput -> {
                    log.info("Order Detail output: " + orderDetailOutput);
                    if ( orderDetailOutput.getError() != null && orderDetailOutput.getError().length() > 0 ) {
                        log.error("Error while extracting order detail: " + orderDetailOutput.getMessage());
                        throw new RuntimeException("Error while extracting order detail: " + orderDetailOutput.getMessage());
                    }
                    return true;
                });
    }

    private Multi<OrderShopee> getOrderDetailWithEscrow(Linking linking, GetOrderDetailOutput output) {
        return Multi.createFrom()
                .iterable(output.getResponse().getOrder_list())
                .flatMap(orderDetailOutput ->
                        getOrderEscrow(linking, orderDetailOutput.getOrder_sn())
                                .map(escrow -> OrderShopeeTransformer.from(orderDetailOutput, escrow))
                                .toMulti()
                );
    }

    private Uni<GetEscrowDetailOutput> getOrderEscrow(Linking linking, String orderSn) {
        return Uni.createFrom()
                .item(orderSn)
                .flatMap(mOrderSn -> {
                    final int partnerId = Integer.parseInt(linking.partnerId);
                    final int shopId = Integer.parseInt(linking.shopId);

                    final ShopApiCommonParam commonParam = new ShopApiCommonParam(
                            partnerId, linking.partnerSecret,
                            linking.accessToken, shopId
                    );

                    GetEscrowDetailInput input = new GetEscrowDetailInput(mOrderSn);
                    return paymentService.getEscrowDetail(commonParam, input);
                });
    }

}
