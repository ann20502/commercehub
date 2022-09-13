package com.commercehub.etl.detail.usecase.shop;

import com.commercehub.common.ShopeeTimeZone;
import com.commercehub.etl.core.usecase.shop.TimeZoneProvider;

import javax.enterprise.context.Dependent;

@Dependent
public class TimeZoneProviderShopee implements TimeZoneProvider {

    @Override
    public String provideTimeZoneId(String shopRegion) {
        return ShopeeTimeZone.getTimeZone(shopRegion);
    }

}
