package com.commercehub.etl.detail.usecaseinteractor.shop;

import com.commercehub.etl.core.usecase.shop.TimeZoneProvider;
import com.commercehub.etl.core.usecase.shop.TimeZoneProviderFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class TimeZoneProviderFactoryInteractor implements TimeZoneProviderFactory {

    @Inject
    TimeZoneProviderShopee shopee;

    @Override
    public TimeZoneProvider dispatch(String platform) {
        return shopee;
    }

}
