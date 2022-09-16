package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.TopSelling;
import com.commercehub.rs.core.entity.input.TopSellingInput;
import com.commercehub.rs.core.entitybuilder.inputbuilder.TopSellingInputBuilder;
import com.commercehub.rs.core.usecase.GetTopSelling;
import com.commercehub.rs.core.usecase.TopSellingCalculatorFactory;
import com.commercehub.rs.core.usecase.boundary.TopSellingInputBoundary;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import java.time.LocalDate;

@Dependent
public class GetTopSellingInteractor implements GetTopSelling, TopSellingInputBoundary {

    private final TopSellingInputBuilder builder = new TopSellingInputBuilder();

    private final TopSellingCalculatorFactory factory;

    public GetTopSellingInteractor(TopSellingCalculatorFactory factory) {
        this.factory = factory;
    }

    @Override
    public TopSellingInputBoundary platform(String platform) {
        builder.setPlatform(platform);
        return this;
    }

    @Override
    public TopSellingInputBoundary shopId(String shopId) {
        builder.setShopId(shopId);
        return this;
    }

    @Override
    public TopSellingInputBoundary from(LocalDate from) {
        builder.setFrom(from);
        return this;
    }

    @Override
    public TopSellingInputBoundary to(LocalDate to) {
        builder.setTo(to);
        return this;
    }

    @Override
    public TopSellingInputBoundary zone(String zone) {
        builder.setZone(zone);
        return this;
    }

    @Override
    public Uni<TopSelling> execute() {
        final int NO_OF_ITEM = 5;
        return execute(builder.createTopSellingInput(), NO_OF_ITEM);
    }

    @Override
    public Uni<TopSelling> execute(TopSellingInput input, int noOfItem) {
        return Uni.createFrom()
                .item(() -> factory.getCalculator(input.platform))
                .map(calculator -> calculator.calculate(input, noOfItem));
    }

}
