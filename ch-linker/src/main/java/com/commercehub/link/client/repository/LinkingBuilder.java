package com.commercehub.link.client.repository;

import java.util.Date;

public class LinkingBuilder {

    private String status;
    private String platform;
    private String partnerId;
    private String partnerSecret;
    private String shopId;
    private String shopName;
    private String shopStatus;
    private String shopRegion;
    private String accessToken;
    private Date accessTokenExpiry;
    private String refreshToken;
    private Date refreshTokenExpiry;
    private boolean link;
    private boolean setup;
    private Date businessStartDate;

    public LinkingBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public LinkingBuilder setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public LinkingBuilder setPartnerId(String partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public LinkingBuilder setPartnerSecret(String partnerSecret) {
        this.partnerSecret = partnerSecret;
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

    public LinkingBuilder setLink(boolean link) {
        this.link = link;
        return this;
    }

    public LinkingBuilder setSetup(boolean setup) {
        this.setup = setup;
        return this;
    }

    public LinkingBuilder setBusinessStartDate(Date businessStartDate) {
        this.businessStartDate = businessStartDate;
        return this;
    }

    public Linking createLinking() {
        return new Linking(status, platform, partnerId, partnerSecret, shopId, shopName, shopStatus, shopRegion, accessToken, accessTokenExpiry, refreshToken, refreshTokenExpiry, link, setup, businessStartDate);
    }

}