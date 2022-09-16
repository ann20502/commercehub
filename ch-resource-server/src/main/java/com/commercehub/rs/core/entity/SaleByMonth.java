package com.commercehub.rs.core.entity;

import java.math.BigDecimal;
import java.time.YearMonth;

public class SaleByMonth {

    public final YearMonth yearMonth;
    public final BigDecimal total;

    public SaleByMonth(YearMonth yearMonth, BigDecimal total) {
        this.yearMonth = yearMonth;
        this.total = total;
    }

}
