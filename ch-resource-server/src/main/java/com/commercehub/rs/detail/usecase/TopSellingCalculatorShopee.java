package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.TopSelling;
import com.commercehub.rs.core.entity.input.TopSellingInput;
import com.commercehub.rs.core.usecase.TopSellingCalculator;
import com.commercehub.rs.detail.repository.BQTopSellingRepositoryShopee;

import javax.enterprise.context.Dependent;

@Dependent
public class TopSellingCalculatorShopee implements TopSellingCalculator {

    private final BQTopSellingRepositoryShopee repositoryShopee;

    public TopSellingCalculatorShopee(BQTopSellingRepositoryShopee repositoryShopee) {
        this.repositoryShopee = repositoryShopee;
    }

    @Override
    public TopSelling calculate(TopSellingInput input, int noOfItem) {
        return repositoryShopee.execute(input, noOfItem);
    }

}
