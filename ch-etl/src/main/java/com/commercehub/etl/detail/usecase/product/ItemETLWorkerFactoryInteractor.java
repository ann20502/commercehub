package com.commercehub.etl.detail.usecase.product;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.usecase.product.ItemETLWorker;
import com.commercehub.etl.core.usecase.product.ItemETLWorkerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ItemETLWorkerFactoryInteractor implements ItemETLWorkerFactory {

    @Inject
    ItemETLWorkerShopee workerShopee;

    @Override
    public ItemETLWorker dispatch(String platform) {
        return platform.equals(Linking.PLATFORM_SHOPEE) ? workerShopee : null;
    }

}
