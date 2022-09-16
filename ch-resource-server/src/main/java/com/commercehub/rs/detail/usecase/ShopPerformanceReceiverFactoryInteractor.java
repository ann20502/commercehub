package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.Linking;
import com.commercehub.rs.core.usecase.ShopPerformanceReceiver;
import com.commercehub.rs.core.usecase.ShopPerformanceReceiverFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ShopPerformanceReceiverFactoryInteractor implements ShopPerformanceReceiverFactory {

    @Inject
    ShopPerformanceReceiverShopee receiverShopee;

    @Override
    public ShopPerformanceReceiver dispatch(String platform) {
        return Linking.PLATFORM_SHOPEE.equals(platform) ? receiverShopee : null;
    }

}
