package com.commercehub.linker.core.entity;

import java.time.Instant;

public class Linking {

    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_INACTIVE = "I";

    public static final String PLATFORM_SHOPEE = "shopee";

    public final String id;
    public final String status;

    public final String partnerId;
    public final String platform;
    public final String shopId;
    public final String shopName;
    public final String shopStatus;
    public final String shopRegion;

    public final boolean hasLink;
    public final boolean hasSetup;
    public final Instant businessStartDate;

    public Linking(String id, String status, String partnerId, String platform, String shopId, String shopName, String shopStatus, String shopRegion, boolean hasLink, boolean hasSetup, Instant businessStartDate) {
        this.id = id;
        this.status = status;
        this.partnerId = partnerId;
        this.platform = platform;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopStatus = shopStatus;
        this.shopRegion = shopRegion;
        this.hasLink = hasLink;
        this.hasSetup = hasSetup;
        this.businessStartDate = businessStartDate;
    }

    @Override
    public String toString() {
        return "Linking{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", platform='" + platform + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopStatus='" + shopStatus + '\'' +
                ", shopRegion='" + shopRegion + '\'' +
                ", hasLink=" + hasLink +
                ", hasSetup=" + hasSetup +
                ", businessStartDate=" + businessStartDate +
                '}';
    }

}
