package com.commercehub.rs.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalesByCalendar {

    public final LocalDate date;
    public final BigDecimal total;

    public SalesByCalendar(LocalDate date, BigDecimal total) {
        this.date = date;
        this.total = total;
    }

}
