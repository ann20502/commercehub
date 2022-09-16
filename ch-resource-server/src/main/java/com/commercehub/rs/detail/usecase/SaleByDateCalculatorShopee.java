package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.SaleByDate;
import com.commercehub.rs.core.entity.input.GetSaleInput;
import com.commercehub.rs.core.usecase.SaleByDateCalculator;
import com.commercehub.rs.detail.repository.BQSaleByDateRepositoryShopee;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class SaleByDateCalculatorShopee implements SaleByDateCalculator {

    @Inject
    BQSaleByDateRepositoryShopee repositoryShopee;

    @Override
    public List<SaleByDate> calculate(GetSaleInput input) {
        return repositoryShopee.execute(input);
    }

}
