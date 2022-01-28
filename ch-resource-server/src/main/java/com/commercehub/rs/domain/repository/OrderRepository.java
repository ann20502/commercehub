package com.commercehub.rs.domain.repository;

import com.commercehub.rs.domain.entity.SalesByCalendar;
import com.commercehub.rs.domain.entity.TopSelling;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {

    public List<SalesByCalendar> getSalesByMonth(String dataset, LocalDate from, LocalDate to, String zone);

    public List<SalesByCalendar> getSalesByDate(String dataset, LocalDate from, LocalDate to, String zone);

    public TopSelling getTopSelling(String dataset, LocalDate from, LocalDate to, String zone);

}
