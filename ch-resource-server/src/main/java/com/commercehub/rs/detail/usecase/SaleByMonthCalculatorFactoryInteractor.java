package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.Linking;
import com.commercehub.rs.core.usecase.SaleByMonthCalculator;
import com.commercehub.rs.core.usecase.SaleByMonthCalculatorFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class SaleByMonthCalculatorFactoryInteractor implements SaleByMonthCalculatorFactory {

    @Inject
    SaleByMonthCalculatorShopee calculatorShopee;

    @Override
    public SaleByMonthCalculator dispatch(String platform) {
        return Linking.PLATFORM_SHOPEE.equals(platform) ? calculatorShopee : null;
    }

}
