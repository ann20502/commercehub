package com.commercehub.rs.core.usecase.boundary;

import com.commercehub.rs.core.entity.SaleByDate;
import io.smallrye.mutiny.Uni;

import java.time.LocalDate;
import java.util.List;

public interface GetSaleInputBoundary {

    GetSaleInputBoundary groupBy(String groupBy);
    GetSaleInputBoundary platform(String platform);
    GetSaleInputBoundary shopId(String shopId);
    GetSaleInputBoundary from(LocalDate from);
    GetSaleInputBoundary to(LocalDate to);
    GetSaleInputBoundary zone(String zone);
    Uni<List<SaleByDate>> execute();

}
