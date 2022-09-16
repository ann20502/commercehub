package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.SaleByDate;
import com.commercehub.rs.core.entity.SaleByMonth;
import com.commercehub.rs.core.entity.input.GetSaleInput;
import com.commercehub.rs.core.entitybuilder.inputbuilder.GetSaleInputBuilder;
import com.commercehub.rs.core.usecase.GetSale;
import com.commercehub.rs.core.usecase.SaleByDateCalculatorFactory;
import com.commercehub.rs.core.usecase.SaleByMonthCalculatorFactory;
import com.commercehub.rs.core.usecase.boundary.GetSaleInputBoundary;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class GetSaleInteractor implements GetSale, GetSaleInputBoundary {

    private final GetSaleInputBuilder builder = new GetSaleInputBuilder();

    @Inject
    SaleByDateCalculatorFactory saleByDateCalculatorFactory;

    @Inject
    SaleByMonthCalculatorFactory saleByMonthCalculatorFactory;

    @Override
    public Uni<List<SaleByDate>> execute(GetSaleInput getSaleInput) {
        return Uni.createFrom().item(getSaleInput)
                .flatMap(input -> {
                    switch(input.groupBy) {
                        case GetSale.GROUP_BY_DATE:
                            return getSaleByDate(input);

                        case GetSale.GROUP_BY_MONTH:
                            return getSaleByMonth(input);

                        default:
                            throw new RuntimeException("Unknown sale type");
                    }
                });
    }

    private Uni<List<SaleByDate>> getSaleByDate(GetSaleInput input) {
        return Uni.createFrom()
                .item(() -> saleByDateCalculatorFactory.dispatch(input.platform))
                .map(calculator -> calculator.calculate(input));
    }

    private Uni<List<SaleByDate>> getSaleByMonth(GetSaleInput input) {
        return Uni.createFrom()
                .item(() -> saleByMonthCalculatorFactory.dispatch(input.platform))
                .map(calculator -> calculator.calculate(input))
                .map(sales -> {
                    List<SaleByDate> result = new ArrayList<>();
                    for ( SaleByMonth sale : sales ) {
                        result.add(
                                new SaleByDate(
                                        sale.yearMonth.atDay(1),
                                        sale.total
                                )
                        );
                    }
                    return result;
                });
    }

    @Override
    public GetSaleInputBoundary groupBy(String groupBy) {
        builder.setGroupBy(groupBy);
        return this;
    }

    @Override
    public GetSaleInputBoundary platform(String platform) {
        builder.setPlatform(platform);
        return this;
    }

    @Override
    public GetSaleInputBoundary shopId(String shopId) {
        builder.setShopId(shopId);
        return this;
    }

    @Override
    public GetSaleInputBoundary from(LocalDate from) {
        builder.setFrom(from);
        return this;
    }

    @Override
    public GetSaleInputBoundary to(LocalDate to) {
        builder.setTo(to);
        return this;
    }

    @Override
    public GetSaleInputBoundary zone(String zone) {
        builder.setZone(zone);
        return this;
    }

    @Override
    public Uni<List<SaleByDate>> execute() {
        return execute(builder.createGetSaleInput());
    }
}
