package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.Linking;
import com.commercehub.rs.core.usecase.SaleByDateCalculator;
import com.commercehub.rs.core.usecase.SaleByDateCalculatorFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class SaleByDateCalculatorFactoryInteractor implements SaleByDateCalculatorFactory {

    @Inject
    SaleByDateCalculatorShopee calculatorShopee;

    @Override
    public SaleByDateCalculator dispatch(String platform) {
        return Linking.PLATFORM_SHOPEE.equals(platform) ? calculatorShopee : null;
    }

}
