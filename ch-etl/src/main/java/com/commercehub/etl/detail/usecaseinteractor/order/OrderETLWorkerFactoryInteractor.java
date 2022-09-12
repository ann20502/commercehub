package com.commercehub.etl.detail.usecaseinteractor.order;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.usecase.order.OrderETLWorker;
import com.commercehub.etl.core.usecase.order.OrderETLWorkerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class OrderETLWorkerFactoryInteractor implements OrderETLWorkerFactory {

    @Inject
    OrderETLWorkerShopee workerShopee;

    @Override
    public OrderETLWorker dispatch(String platform) {
        return platform.equals(Linking.PLATFORM_SHOPEE) ? workerShopee : null;
    }

}
