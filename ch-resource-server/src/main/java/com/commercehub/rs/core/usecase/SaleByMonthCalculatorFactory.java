package com.commercehub.rs.core.usecase;

public interface SaleByMonthCalculatorFactory {

    SaleByMonthCalculator dispatch(String platform);

}
