package com.commercehub.etl.domain.entity;

import java.util.Date;

public class Linking {

    public static final String PLATFORM_SHOPEE = "shopee";

    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_INACTIVE = "I";

    private String platform;
    private String status;
    private String accessToken;
    private Date accessTokenExpiry;
    private String refreshToken;
    private Date refreshTokenExpiry;
    private String shopId;
    private String shopName;
    private String shopStatus;
    private String shopRegion;

    public Linking() {}

    public Linking(String platform, String status, String accessToken, Date accessTokenExpiry, String refreshToken, Date refreshTokenExpiry, String shopId, String shopName, String shopStatus, String shopRegion) {
        this.platform = platform;
        this.status = status;
        this.accessToken = accessToken;
        this.accessTokenExpiry = accessTokenExpiry;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiry = refreshTokenExpiry;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopStatus = shopStatus;
        this.shopRegion = shopRegion;
    }

    public String getPlatform() {
        return platform;
    }

    public String getStatus() {
        return status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Date getAccessTokenExpiry() {
        return accessTokenExpiry;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Date getRefreshTokenExpiry() {
        return refreshTokenExpiry;
    }

    public String getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public String getShopRegion() {
        return shopRegion;
    }

    @Override
    public String toString() {
        return "Linking{" +
                "platform='" + platform + '\'' +
                ", status='" + status + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", accessTokenExpiry=" + accessTokenExpiry +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenExpiry=" + refreshTokenExpiry +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopStatus='" + shopStatus + '\'' +
                ", shopRegion='" + shopRegion + '\'' +
                '}';
    }

}
