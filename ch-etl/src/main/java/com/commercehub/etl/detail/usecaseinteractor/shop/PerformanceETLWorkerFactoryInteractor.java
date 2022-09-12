package com.commercehub.etl.detail.usecaseinteractor.shop;

import com.commercehub.etl.core.usecase.shop.PerformanceETLWorker;
import com.commercehub.etl.core.usecase.shop.PerformanceETLWorkerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class PerformanceETLWorkerFactoryInteractor implements PerformanceETLWorkerFactory {

    @Inject
    PerformanceETLWorkerShopee workerShopee;

    @Override
    public PerformanceETLWorker dispatch(String platform) {
        return workerShopee;
    }

}
