package com.commercehub.link.client.persistence;

import com.commercehub.link.client.shopee.LinkClientShopee;

import java.util.Date;

public class LinkingShopeeBuilder {

    private String accessToken;
    private Date accessTokenExpiry;
    private String refreshToken;
    private Date refreshTokenExpiry;
    private final String shopId;
    private final String shopName;
    private final String status;
    private String shopStatus;
    private String shopRegion;

    public LinkingShopeeBuilder(String shopId, String shopName, String status) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.status = status;
    }

    public LinkingShopeeBuilder setAccessToken(String accessToken, Date accessTokenExpiry) {
        this.accessToken = accessToken;
        this.accessTokenExpiry = accessTokenExpiry;
        return this;
    }

    public LinkingShopeeBuilder setRefreshToken(String refreshToken, Date refreshTokenExpiry) {
        this.refreshToken = refreshToken;
        this.refreshTokenExpiry = refreshTokenExpiry;
        return this;
    }

    public LinkingShopeeBuilder setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
        return this;
    }

    public LinkingShopeeBuilder setShopRegion(String shopRegion) {
        this.shopRegion = shopRegion;
        return this;
    }

    public LinkingShopee createLinkingShopee() {
        return new LinkingShopee(status, LinkClientShopee.NAME, accessToken, accessTokenExpiry, refreshToken, refreshTokenExpiry, shopId, shopName, shopStatus, shopRegion);
    }
}