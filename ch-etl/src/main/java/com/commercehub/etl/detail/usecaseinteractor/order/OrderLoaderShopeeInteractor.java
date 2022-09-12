package com.commercehub.etl.detail.usecaseinteractor.order;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.order.OrderShopee;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.repository.OrderRepositoryShopee;
import com.commercehub.etl.core.repository.TimedTaskRepository;
import com.commercehub.etl.core.usecase.order.OrderLoaderShopee;
import com.commercehub.etl.detail.exception.ETLRuntimeException;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.Instant;
import java.util.List;

@Dependent
public class OrderLoaderShopeeInteractor implements OrderLoaderShopee {

    @Inject
    TimedTaskRepository timedTaskRepository;

    @Inject
    OrderRepositoryShopee orderRepositoryShopee;

    @Override
    public Uni<Boolean> load(Linking linking, String collectionName, TimedTask task, List<OrderShopee> input) {
        return Uni.createFrom()
                .item(input)
                .onItem().invoke(() -> setTaskToEnd(collectionName, task.id))
                .flatMap(orders -> saveOrder(linking, task, orders))
                .map(mOrders -> Boolean.TRUE);
    }

    private void setTaskToEnd(String collectionName, String documentId) {
        boolean result = timedTaskRepository.setToEnd(collectionName, documentId, Instant.now());
        if (!result) { throw new RuntimeException("Failed to update task status"); }
    }

    private Uni<List<OrderShopee>> saveOrder(Linking linking, TimedTask task, List<OrderShopee> orders) {
        return Uni.createFrom()
                .item(() -> {
                    if ( orders.isEmpty() ) { return orders; }
                    boolean result = orderRepositoryShopee.add(linking.platform, linking.shopId, orders);
                    if (!result) { throw new ETLRuntimeException("Failed to save order update"); }
                    return orders;
                });
    }

}
