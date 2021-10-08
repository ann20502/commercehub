package com.commercehub.etl.domain.entity;

import java.util.Date;

public class LinkingBuilder {

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

    public LinkingBuilder() {}

    public LinkingBuilder(Linking linking) {
        this.platform = linking.getPlatform();
        this.status = linking.getStatus();
        this.accessToken = linking.getAccessToken();
        this.accessTokenExpiry = linking.getAccessTokenExpiry();
        this.refreshToken = linking.getRefreshToken();
        this.refreshTokenExpiry = linking.getRefreshTokenExpiry();
        this.shopId = linking.getShopId();
        this.shopName = linking.getShopName();
        this.shopStatus = linking.getShopStatus();
        this.shopRegion = linking.getShopRegion();
    }

    public LinkingBuilder setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public LinkingBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public LinkingBuilder setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public LinkingBuilder setAccessTokenExpiry(Date accessTokenExpiry) {
        this.accessTokenExpiry = accessTokenExpiry;
        return this;
    }

    public LinkingBuilder setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public LinkingBuilder setRefreshTokenExpiry(Date refreshTokenExpiry) {
        this.refreshTokenExpiry = refreshTokenExpiry;
        return this;
    }

    public LinkingBuilder setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public LinkingBuilder setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public LinkingBuilder setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
        return this;
    }

    public LinkingBuilder setShopRegion(String shopRegion) {
        this.shopRegion = shopRegion;
        return this;
    }

    public Linking createLinking() {
        return new Linking(platform, status, accessToken, accessTokenExpiry, refreshToken, refreshTokenExpiry, shopId, shopName, shopStatus, shopRegion);
    }
}