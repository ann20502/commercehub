package com.commercehub.rs.core.usecase;

import com.commercehub.rs.core.entity.SaleByDate;
import com.commercehub.rs.core.entity.input.GetSaleInput;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface GetSale {

    public static final String GROUP_BY_MONTH = "month";
    public static final String GROUP_BY_DATE = "date";
    public static final String REGEX_GROUP = GROUP_BY_MONTH + "|" + GROUP_BY_DATE;

    Uni<List<SaleByDate>> execute(GetSaleInput input);

}
