package com.commercehub.etl.detail.usecase.order;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.usecase.order.OrderETLWorker;
import com.commercehub.etl.core.usecase.order.OrderExtractorShopee;
import com.commercehub.etl.core.usecase.order.OrderLoaderShopee;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskGroupNameProvider;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskNameProviderShopeeNewOrder;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;

@Dependent
public class OrderETLWorkerShopee implements OrderETLWorker {

    private final TimedTaskGroupNameProvider groupNameProvider;
    private final TimedTaskNameProviderShopeeNewOrder nameProviderShopeeNewOrder;
    private final OrderExtractorShopee orderExtractorShopee;
    private final OrderLoaderShopee orderLoaderShopee;

    public OrderETLWorkerShopee(TimedTaskGroupNameProvider groupNameProvider, TimedTaskNameProviderShopeeNewOrder nameProviderShopeeNewOrder, OrderExtractorShopee orderExtractorShopee, OrderLoaderShopee orderLoaderShopee) {
        this.groupNameProvider = groupNameProvider;
        this.nameProviderShopeeNewOrder = nameProviderShopeeNewOrder;
        this.orderExtractorShopee = orderExtractorShopee;
        this.orderLoaderShopee = orderLoaderShopee;
    }

    @Override
    public Uni<Boolean> extractTransformLoad(Linking linking, TimedTask task) {
        return orderExtractorShopee
                .extract(linking, task, OrderExtractorShopee.ExtractionType.NEW)
                .flatMap(orders -> orderLoaderShopee.load(
                        linking,
                        groupNameProvider.provide(nameProviderShopeeNewOrder.provide()),
                        task,
                        orders
                ));
    }

    @Override
    public String taskName() {
        return nameProviderShopeeNewOrder.provide();
    }

}
