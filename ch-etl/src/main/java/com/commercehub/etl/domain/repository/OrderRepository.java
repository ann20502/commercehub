package com.commercehub.etl.domain.repository;

import com.commercehub.etl.domain.entity.order.Order;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface OrderRepository {

    Uni<Boolean> add(String platform, String shopId, List<Order> orders);

}
