package com.commercehub.etl.vm;

import com.commercehub.etl.domain.entity.order.Order;
import com.commercehub.etl.domain.usecase.order.ExtractNewOrderShopee;
import com.commercehub.etl.domain.usecase.order.ExtractOrderUpdateShopee;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class OrderViewModel {

    @Inject
    ExtractNewOrderShopee extractNewOrderShopee;

    @Inject
    ExtractOrderUpdateShopee extractOrderUpdateShopee;

    public Uni<List<Order>> extractNewOrderShopee(String documentId) {
        return extractNewOrderShopee.extract(documentId);
    }

    public Uni<List<Order>> extractOrderUpdateShopee(String documentId) {
        return extractOrderUpdateShopee.extract(documentId);
    }

}
