package com.commercehub.rs.domain.repository;

import com.commercehub.rs.domain.entity.ShopPerformance;

public interface ShopRepository {

    public ShopPerformance getShopPerformance(String dataset);

}
