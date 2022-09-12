package com.commercehub.etl.detail.usecaseinteractor.order;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.usecase.order.OrderExtractorShopee;
import com.commercehub.etl.core.usecase.order.OrderLoaderShopee;
import com.commercehub.etl.core.usecase.order.OrderUpdateETLWorker;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskGroupNameProvider;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskNameProviderShopeeOrderUpdate;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;

@Dependent
public class OrderUpdateETLWorkerShopee implements OrderUpdateETLWorker {

    private final TimedTaskNameProviderShopeeOrderUpdate nameProviderShopeeOrderUpdate;
    private final TimedTaskGroupNameProvider groupNameProvider;
    private final OrderExtractorShopee orderExtractorShopee;
    private final OrderLoaderShopee orderLoaderShopee;

    public OrderUpdateETLWorkerShopee(TimedTaskNameProviderShopeeOrderUpdate nameProviderShopeeOrderUpdate, TimedTaskGroupNameProvider groupNameProvider, OrderExtractorShopee orderExtractorShopee, OrderLoaderShopee orderLoaderShopee) {
        this.nameProviderShopeeOrderUpdate = nameProviderShopeeOrderUpdate;
        this.groupNameProvider = groupNameProvider;
        this.orderExtractorShopee = orderExtractorShopee;
        this.orderLoaderShopee = orderLoaderShopee;
    }

    @Override
    public Uni<Boolean> extractTransformLoad(Linking linking, TimedTask task) {
        return orderExtractorShopee
                .extract(linking, task, OrderExtractorShopee.ExtractionType.UPDATE)
                .flatMap(orders -> orderLoaderShopee.load(
                        linking,
                        groupNameProvider.provide(nameProviderShopeeOrderUpdate.provide()),
                        task,
                        orders
                ));
    }

    @Override
    public String taskName() {
        return nameProviderShopeeOrderUpdate.provide();
    }

}
