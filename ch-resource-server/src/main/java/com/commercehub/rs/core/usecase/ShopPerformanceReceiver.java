package com.commercehub.rs.core.usecase;

import com.commercehub.rs.core.entity.ShopPerformance;

public interface ShopPerformanceReceiver {

    ShopPerformance execute(String platform, String shopId);

}
