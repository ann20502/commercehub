package com.commercehub.rs.core.repository;

import com.commercehub.rs.core.entity.SaleByMonth;
import com.commercehub.rs.core.entity.input.GetSaleInput;

import java.util.List;

public interface SaleByMonthRepository {

    List<SaleByMonth> execute(GetSaleInput input);

}
