package com.commercehub.rs.core.usecase.boundary;

import com.commercehub.rs.core.entity.TopSelling;
import io.smallrye.mutiny.Uni;

import java.time.LocalDate;

public interface TopSellingInputBoundary {

    TopSellingInputBoundary platform(String platform);
    TopSellingInputBoundary shopId(String shopId);
    TopSellingInputBoundary from(LocalDate from);
    TopSellingInputBoundary to(LocalDate to);
    TopSellingInputBoundary zone(String zone);
    Uni<TopSelling> execute();

}
