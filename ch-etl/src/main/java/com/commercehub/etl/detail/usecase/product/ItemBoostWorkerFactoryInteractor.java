package com.commercehub.etl.detail.usecase.product;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.usecase.product.ItemBoostWorker;
import com.commercehub.etl.core.usecase.product.ItemBoostWorkerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ItemBoostWorkerFactoryInteractor implements ItemBoostWorkerFactory {

    @Inject
    ItemBoostWorkerShopee workerShopee;

    @Override
    public ItemBoostWorker dispatch(String platform) {
        return Linking.PLATFORM_SHOPEE.equals(platform) ? workerShopee : null;
    }

}
