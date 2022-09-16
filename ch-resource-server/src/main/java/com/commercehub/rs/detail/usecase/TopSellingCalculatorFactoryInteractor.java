package com.commercehub.rs.detail.usecase;

import com.commercehub.rs.core.entity.Linking;
import com.commercehub.rs.core.usecase.TopSellingCalculator;
import com.commercehub.rs.core.usecase.TopSellingCalculatorFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class TopSellingCalculatorFactoryInteractor implements TopSellingCalculatorFactory {

    @Inject
    TopSellingCalculatorShopee calculatorShopee;

    @Override
    public TopSellingCalculator getCalculator(String platform) {
        return Linking.PLATFORM_SHOPEE.equals(platform) ? calculatorShopee : null;
    }

}
