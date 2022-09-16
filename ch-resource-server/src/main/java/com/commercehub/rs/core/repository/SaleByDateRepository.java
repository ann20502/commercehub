package com.commercehub.rs.core.repository;

import com.commercehub.rs.core.entity.SaleByDate;
import com.commercehub.rs.core.entity.input.GetSaleInput;

import java.util.List;

public interface SaleByDateRepository {

    List<SaleByDate> execute(GetSaleInput input);

}
