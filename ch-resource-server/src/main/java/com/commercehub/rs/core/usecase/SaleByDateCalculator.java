package com.commercehub.rs.core.usecase;

import com.commercehub.rs.core.entity.SaleByDate;
import com.commercehub.rs.core.entity.input.GetSaleInput;

import java.util.List;

public interface SaleByDateCalculator {

    List<SaleByDate> calculate(GetSaleInput input);

}
