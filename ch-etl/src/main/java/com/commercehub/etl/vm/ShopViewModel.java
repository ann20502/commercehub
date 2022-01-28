package com.commercehub.etl.vm;

import com.commercehub.etl.domain.entity.shop.Performance;
import com.commercehub.etl.domain.usecase.shop.ExtractShopPerformanceShopee;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ShopViewModel {

    @Inject
    ExtractShopPerformanceShopee extractShopPerformanceShopee;

    public Multi<Performance> extractShopPerformance() {
        return extractShopPerformanceShopee.extract();
    }

}
