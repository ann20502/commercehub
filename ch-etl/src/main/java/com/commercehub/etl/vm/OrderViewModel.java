package com.commercehub.etl.vm;

import com.commercehub.etl.domain.entity.order.Order;
import com.commercehub.etl.domain.usecase.order.ExtractNewOrderShopee;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class OrderViewModel {

    @Inject
    ExtractNewOrderShopee extractNewOrderShopee;

    public Uni<List<Order>> extractNewOrderShopee(String documentId) {
        return extractNewOrderShopee.extract(documentId);
    }

}
