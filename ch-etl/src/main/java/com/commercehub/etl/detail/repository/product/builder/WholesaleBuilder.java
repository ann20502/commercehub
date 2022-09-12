package com.commercehub.etl.detail.repository.product.builder;

import com.commercehub.etl.detail.repository.product.BQItemShopee;

import java.math.BigDecimal;

public class WholesaleBuilder {
    private long min_count;
    private long max_count;
    private BigDecimal unitPrice;
    private BigDecimal inflatedPriceOfUnitPrice;

    public WholesaleBuilder setMin_count(long min_count) {
        this.min_count = min_count;
        return this;
    }

    public WholesaleBuilder setMax_count(long max_count) {
        this.max_count = max_count;
        return this;
    }

    public WholesaleBuilder setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public WholesaleBuilder setInflatedPriceOfUnitPrice(BigDecimal inflatedPriceOfUnitPrice) {
        this.inflatedPriceOfUnitPrice = inflatedPriceOfUnitPrice;
        return this;
    }

    public BQItemShopee.Wholesale createWholesale() {
        return new BQItemShopee.Wholesale(min_count, max_count, unitPrice, inflatedPriceOfUnitPrice);
    }
}