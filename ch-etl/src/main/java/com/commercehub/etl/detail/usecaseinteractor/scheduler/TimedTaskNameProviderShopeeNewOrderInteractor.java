package com.commercehub.etl.detail.usecaseinteractor.scheduler;

import com.commercehub.etl.core.usecase.scheduler.TimedTaskNameProviderShopeeNewOrder;

import javax.enterprise.context.Dependent;

@Dependent
public class TimedTaskNameProviderShopeeNewOrderInteractor implements TimedTaskNameProviderShopeeNewOrder {

    @Override
    public String provide() {
        return "ShopeeNewOrder";
    }

}
