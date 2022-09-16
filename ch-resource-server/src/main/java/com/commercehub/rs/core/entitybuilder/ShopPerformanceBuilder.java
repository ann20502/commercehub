package com.commercehub.rs.core.entitybuilder;

import com.commercehub.rs.core.entity.ShopPerformance;

import java.time.Instant;

public class ShopPerformanceBuilder {

    private Instant extractTime = Instant.MIN;
    private long overallPerformance = 0L;
    private ShopPerformance.Data rating = new ShopPerformance.Data("", "", "");
    private ShopPerformance.Data fulfillmentPreparationTime = new ShopPerformance.Data("", "", "");
    private ShopPerformance.Data chatResponseTime = new ShopPerformance.Data("", "", "");

    public ShopPerformanceBuilder setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
        return this;
    }

    public ShopPerformanceBuilder setOverallPerformance(long overallPerformance) {
        this.overallPerformance = overallPerformance;
        return this;
    }

    public ShopPerformanceBuilder setRating(ShopPerformance.Data rating) {
        this.rating = rating;
        return this;
    }

    public ShopPerformanceBuilder setFulfillmentPreparationTime(ShopPerformance.Data fulfillmentPreparationTime) {
        this.fulfillmentPreparationTime = fulfillmentPreparationTime;
        return this;
    }

    public ShopPerformanceBuilder setChatResponseTime(ShopPerformance.Data chatResponseTime) {
        this.chatResponseTime = chatResponseTime;
        return this;
    }

    public ShopPerformance createShopPerformance() {
        return new ShopPerformance(extractTime, overallPerformance, rating, fulfillmentPreparationTime, chatResponseTime);
    }
}