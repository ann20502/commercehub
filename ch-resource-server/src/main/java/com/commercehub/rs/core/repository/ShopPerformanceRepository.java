package com.commercehub.rs.core.repository;

import com.commercehub.rs.core.entity.ShopPerformance;

public interface ShopPerformanceRepository {

    ShopPerformance execute(String platform, String shopId);

}
