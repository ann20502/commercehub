package com.commercehub.rs.core.usecase;

import com.commercehub.rs.core.entity.ShopPerformance;
import io.smallrye.mutiny.Uni;

public interface GetShopPerformance {

    Uni<ShopPerformance> execute(String platform, String shopId);

}
