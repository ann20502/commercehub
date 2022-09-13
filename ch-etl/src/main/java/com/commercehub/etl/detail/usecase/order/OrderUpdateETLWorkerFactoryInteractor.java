package com.commercehub.etl.detail.usecase.order;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.usecase.order.OrderUpdateETLWorker;
import com.commercehub.etl.core.usecase.order.OrderUpdateETLWorkerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class OrderUpdateETLWorkerFactoryInteractor implements OrderUpdateETLWorkerFactory {

    @Inject
    OrderUpdateETLWorkerShopee workerShopee;

    @Override
    public OrderUpdateETLWorker dispatch(String platform) {
        return platform.equals(Linking.PLATFORM_SHOPEE) ? workerShopee : null;
    }

}
