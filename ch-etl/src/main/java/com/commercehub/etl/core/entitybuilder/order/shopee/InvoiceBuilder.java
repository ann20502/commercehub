package com.commercehub.etl.core.entitybuilder.order.shopee;

import com.commercehub.etl.core.entity.order.OrderShopee;

import java.math.BigDecimal;
import java.time.Instant;

public class InvoiceBuilder {
    private String number;
    private String seriesNUmber;
    private String accessKey;
    private Instant issueDate;
    private BigDecimal totalValue;
    private BigDecimal productsTotalValue;
    private String taxCode;

    public InvoiceBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    public InvoiceBuilder setSeriesNUmber(String seriesNUmber) {
        this.seriesNUmber = seriesNUmber;
        return this;
    }

    public InvoiceBuilder setAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public InvoiceBuilder setIssueDate(Instant issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public InvoiceBuilder setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public InvoiceBuilder setProductsTotalValue(BigDecimal productsTotalValue) {
        this.productsTotalValue = productsTotalValue;
        return this;
    }

    public InvoiceBuilder setTaxCode(String taxCode) {
        this.taxCode = taxCode;
        return this;
    }

    public OrderShopee.Invoice createInvoice() {
        return new OrderShopee.Invoice(number, seriesNUmber, accessKey, issueDate, totalValue, productsTotalValue, taxCode);
    }
}