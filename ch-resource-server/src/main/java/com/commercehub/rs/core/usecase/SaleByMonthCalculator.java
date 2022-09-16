package com.commercehub.rs.core.usecase;

import com.commercehub.rs.core.entity.SaleByMonth;
import com.commercehub.rs.core.entity.input.GetSaleInput;

import java.util.List;

public interface SaleByMonthCalculator {

    List<SaleByMonth> calculate(GetSaleInput input);

}
