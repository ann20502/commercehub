package com.commercehub.etl.core.repository;

import com.commercehub.etl.core.entity.order.OrderShopee;

import java.util.List;

public interface OrderRepositoryShopee {

    boolean add(String platform, String shopId, List<OrderShopee> orders);

}
