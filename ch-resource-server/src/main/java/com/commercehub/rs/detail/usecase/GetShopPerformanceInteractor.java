package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.ShopPerformance;
import com.commercehub.rs.core.usecase.GetShopPerformance;
import com.commercehub.rs.core.usecase.ShopPerformanceReceiverFactory;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GetShopPerformanceInteractor implements GetShopPerformance {

    @Inject
    ShopPerformanceReceiverFactory factory;

    @Override
    public Uni<ShopPerformance> execute(String platform, String shopId) {
        return Uni.createFrom().item(() -> factory.dispatch(platform))
                .map(receiver -> receiver.execute(platform, shopId));
    }

}
