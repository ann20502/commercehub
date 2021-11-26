package com.commercehub.rs.domain.usecase;

import com.commercehub.rs.domain.entity.SalesByCalendar;
import com.commercehub.rs.domain.entity.SalesByShop;
import com.commercehub.rs.domain.repository.OrderRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDate;

@Dependent
public class GetSalesByShop {

    @Inject
    OrderRepository repository;

    public Uni<SalesByShop> get(String groupBy, String shopId, LocalDate from, LocalDate to, String zone) {
        return Uni.createFrom().item(shopId)
                .map(mShopId -> {
                    switch(groupBy) {
                        case SalesByShop.GROUP_BY_MONTH:
                            return new SalesByShop(mShopId, repository.getSalesByMonth(mShopId, from, to, zone));

                        case SalesByShop.GROUP_BY_DATE:
                            return new SalesByShop(mShopId, repository.getSalesByDate(mShopId, from, to, zone));

                        default:
                            throw new RuntimeException("Unknown sales type");
                    }
                });
    }

}
