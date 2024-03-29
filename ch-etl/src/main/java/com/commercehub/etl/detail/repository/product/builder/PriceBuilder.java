package com.commercehub.etl.detail.repository.product.builder;

import com.commercehub.etl.detail.repository.product.BQItemShopee;

import java.math.BigDecimal;

public class PriceBuilder {
    private String currency;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;
    private BigDecimal inflatedPriceOfCurrentPrice;
    private BigDecimal sipItemPrice;
    private String sipItemPriceSource;

    public PriceBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public PriceBuilder setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public PriceBuilder setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public PriceBuilder setInflatedPriceOfCurrentPrice(BigDecimal inflatedPriceOfCurrentPrice) {
        this.inflatedPriceOfCurrentPrice = inflatedPriceOfCurrentPrice;
        return this;
    }

    public PriceBuilder setSipItemPrice(BigDecimal sipItemPrice) {
        this.sipItemPrice = sipItemPrice;
        return this;
    }

    public PriceBuilder setSipItemPriceSource(String sipItemPriceSource) {
        this.sipItemPriceSource = sipItemPriceSource;
        return this;
    }

    public BQItemShopee.Price createPrice() {
        return new BQItemShopee.Price(currency, originalPrice, currentPrice, inflatedPriceOfCurrentPrice, sipItemPrice, sipItemPriceSource);
    }
}