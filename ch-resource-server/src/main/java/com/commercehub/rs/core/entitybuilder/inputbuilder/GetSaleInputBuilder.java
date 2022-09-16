package com.commercehub.rs.core.entitybuilder.inputbuilder;

import com.commercehub.rs.core.entity.input.GetSaleInput;

import java.time.LocalDate;

public class GetSaleInputBuilder {
    private String groupBy;
    private String platform;
    private String shopId;
    private LocalDate from;
    private LocalDate to;
    private String zone;

    public GetSaleInputBuilder setGroupBy(String groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    public GetSaleInputBuilder setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public GetSaleInputBuilder setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public GetSaleInputBuilder setFrom(LocalDate from) {
        this.from = from;
        return this;
    }

    public GetSaleInputBuilder setTo(LocalDate to) {
        this.to = to;
        return this;
    }

    public GetSaleInputBuilder setZone(String zone) {
        this.zone = zone;
        return this;
    }

    public GetSaleInput createGetSaleInput() {
        return new GetSaleInput(groupBy, platform, shopId, from, to, zone);
    }
}