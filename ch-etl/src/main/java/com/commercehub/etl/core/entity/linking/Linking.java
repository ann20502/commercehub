package com.commercehub.etl.core.entity.linking;

import java.time.Instant;

public class Linking {

    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_INACTIVE = "I";

    public static final String PLATFORM_SHOPEE = "shopee";

    public final String id;
    public final String status;
    public final String platform;
    public final String partnerId;
    public final String partnerSecret;

    public final String shopId;
    public final String shopName;
    public final String shopStatus;
    public final String shopRegion;
    public final String accessToken;
    public final Instant accessTokenExpiry;
    public final String refreshToken;
    public final Instant refreshTokenExpiry;

    public final boolean hasLink;
    public final boolean hasSetup;
    public final Instant businessStartDate;

    public Linking(String id, String status, String platform, String partnerId, String partnerSecret, String shopId, String shopName, String shopStatus, String shopRegion, String accessToken, Instant accessTokenExpiry, String refreshToken, Instant refreshTokenExpiry, boolean hasLink, boolean hasSetup, Instant businessStartDate) {
        this.id = id;
        this.status = status;
        this.platform = platform;
        this.partnerId = partnerId;
        this.partnerSecret = partnerSecret;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopStatus = shopStatus;
        this.shopRegion = shopRegion;
        this.accessToken = accessToken;
        this.accessTokenExpiry = accessTokenExpiry;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiry = refreshTokenExpiry;
        this.hasLink = hasLink;
        this.hasSetup = hasSetup;
        this.businessStartDate = businessStartDate;
    }

    @Override
    public String toString() {
        return "Linking{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", platform='" + platform + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", partnerSecret='" + partnerSecret + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopStatus='" + shopStatus + '\'' +
                ", shopRegion='" + shopRegion + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", accessTokenExpiry=" + accessTokenExpiry +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenExpiry=" + refreshTokenExpiry +
                ", hasLink=" + hasLink +
                ", hasSetup=" + hasSetup +
                ", businessStartDate=" + businessStartDate +
                '}';
    }

}
