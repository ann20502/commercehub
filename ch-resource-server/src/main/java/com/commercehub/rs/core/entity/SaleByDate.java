package com.commercehub.rs.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleByDate {

    public final LocalDate date;
    public final BigDecimal total;

    public SaleByDate(LocalDate date, BigDecimal total) {
        this.date = date;
        this.total = total;
    }

}
