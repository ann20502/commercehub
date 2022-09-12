package com.commercehub.etl.core.repository;

import com.commercehub.etl.core.entity.shop.PerformanceShopee;
import io.smallrye.mutiny.Uni;

public interface ShopRepositoryShopee {

    Uni<Boolean> savePerformance(String platform, String shopId, PerformanceShopee performance);

}
