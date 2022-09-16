package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.SaleByMonth;
import com.commercehub.rs.core.entity.input.GetSaleInput;
import com.commercehub.rs.core.usecase.SaleByMonthCalculator;
import com.commercehub.rs.detail.repository.BQSaleByMonthRepositoryShopee;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class SaleByMonthCalculatorShopee implements SaleByMonthCalculator {

    @Inject
    BQSaleByMonthRepositoryShopee repositoryShopee;

    @Override
    public List<SaleByMonth> calculate(GetSaleInput input) {
        return repositoryShopee.execute(input);
    }

}
