package com.commercehub.rs.core.usecase;

public interface SaleByDateCalculatorFactory {

    SaleByDateCalculator dispatch(String platform);

}
