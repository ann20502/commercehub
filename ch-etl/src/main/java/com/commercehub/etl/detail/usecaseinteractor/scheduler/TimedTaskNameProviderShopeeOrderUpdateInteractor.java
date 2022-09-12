package com.commercehub.etl.detail.usecaseinteractor.scheduler;

import com.commercehub.etl.core.usecase.scheduler.TimedTaskNameProviderShopeeOrderUpdate;

import javax.enterprise.context.Dependent;

@Dependent
public class TimedTaskNameProviderShopeeOrderUpdateInteractor implements TimedTaskNameProviderShopeeOrderUpdate {

    @Override
    public String provide() {
        return "ShopeeOrderUpdate";
    }

}
