package com.commercehub.rs.core.entity.input;

import java.time.LocalDate;

public class TopSellingInput {

    public final String platform;
    public final String shopId;
    public final LocalDate from;
    public final LocalDate to;
    public final String zone;

    public TopSellingInput(String platform, String shopId, LocalDate from, LocalDate to, String zone) {
        this.platform = platform;
        this.shopId = shopId;
        this.from = from;
        this.to = to;
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "TopSellingInput{" +
                "platform='" + platform + '\'' +
                ", shopId='" + shopId + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", zone='" + zone + '\'' +
                '}';
    }
}
