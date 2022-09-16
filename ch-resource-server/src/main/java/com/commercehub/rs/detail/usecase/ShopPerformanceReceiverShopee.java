package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.ShopPerformance;
import com.commercehub.rs.core.usecase.ShopPerformanceReceiver;
import com.commercehub.rs.detail.repository.BQShopPerformanceRepositoryShopee;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ShopPerformanceReceiverShopee implements ShopPerformanceReceiver {

    @Inject
    BQShopPerformanceRepositoryShopee repositoryShopee;

    @Override
    public ShopPerformance execute(String platform, String shopId) {
        return repositoryShopee.execute(platform, shopId);
    }

}
