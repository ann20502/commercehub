package com.commercehub.rs.domain.entity;

import java.util.List;

public class SalesByShop {

    public static final String GROUP_BY_MONTH = "month";
    public static final String GROUP_BY_DATE = "date";
    public static final String REGEX_GROUP = GROUP_BY_MONTH + "|" + GROUP_BY_DATE;

    public final String shopId;
    public final List<SalesByCalendar> sales;

    public SalesByShop(String shopId, List<SalesByCalendar> sales) {
        this.shopId = shopId;
        this.sales = sales;
    }

}
