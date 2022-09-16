package com.commercehub.rs.core.entitybuilder.inputbuilder;

import com.commercehub.rs.core.entity.input.TopSellingInput;

import java.time.LocalDate;

public class TopSellingInputBuilder {
    private String platform;
    private String shopId;
    private LocalDate from;
    private LocalDate to;
    private String zone;

    public TopSellingInputBuilder setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public TopSellingInputBuilder setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public TopSellingInputBuilder setFrom(LocalDate from) {
        this.from = from;
        return this;
    }

    public TopSellingInputBuilder setTo(LocalDate to) {
        this.to = to;
        return this;
    }

    public TopSellingInputBuilder setZone(String zone) {
        this.zone = zone;
        return this;
    }

    public TopSellingInput createTopSellingInput() {
        return new TopSellingInput(platform, shopId, from, to, zone);
    }
}