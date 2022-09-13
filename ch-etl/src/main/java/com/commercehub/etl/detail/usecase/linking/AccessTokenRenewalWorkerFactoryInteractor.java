package com.commercehub.etl.detail.usecase.linking;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.usecase.linking.AccessTokenRenewalWorker;
import com.commercehub.etl.core.usecase.linking.AccessTokenRenewalWorkerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class AccessTokenRenewalWorkerFactoryInteractor implements AccessTokenRenewalWorkerFactory {

    @Inject
    AccessTokenRenewalWorkerShopee workerShopee;

    @Override
    public AccessTokenRenewalWorker dispatch(String platform) {
        return Linking.PLATFORM_SHOPEE.equals(platform) ? workerShopee : null;
    }

}
