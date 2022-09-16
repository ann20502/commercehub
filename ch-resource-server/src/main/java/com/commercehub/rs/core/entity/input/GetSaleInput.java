package com.commercehub.rs.core.entity.input;

import java.time.LocalDate;

public class GetSaleInput {

    public final String groupBy;
    public final String platform;
    public final String shopId;
    public final LocalDate from;
    public final LocalDate to;
    public final String zone;

    public GetSaleInput(String groupBy, String platform, String shopId, LocalDate from, LocalDate to, String zone) {
        this.groupBy = groupBy;
        this.platform = platform;
        this.shopId = shopId;
        this.from = from;
        this.to = to;
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "GetSaleInput{" +
                "groupBy='" + groupBy + '\'' +
                ", platform='" + platform + '\'' +
                ", shopId='" + shopId + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", zone='" + zone + '\'' +
                '}';
    }

}
