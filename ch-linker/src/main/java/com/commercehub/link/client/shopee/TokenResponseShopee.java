package com.commercehub.link.client.shopee;

import com.commercehub.link.client.TokenResponse;

public class TokenResponseShopee implements TokenResponse {

    private final String requestId;
    private final String shopId;
    private final String shopName;
    private final String shopStatus;
    private final String shopRegion;
    private final String accessToken;
    private final Long accessTokenExpiry;
    private final String refreshToken;
    private final int[] merchantIds;
    private final int[] shopIds;

    public TokenResponseShopee(String requestId, String shopId, String shopName, String shopStatus, String shopRegion, String accessToken, Long accessTokenExpiry, String refreshToken, int[] merchantIds, int[] shopIds) {
        this.requestId = requestId;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopStatus = shopStatus;
        this.shopRegion = shopRegion;
        this.accessToken = accessToken;
        this.accessTokenExpiry = accessTokenExpiry;
        this.refreshToken = refreshToken;
        this.merchantIds = merchantIds;
        this.shopIds = shopIds;
    }

    @Override
    public String accessToken() {
        return accessToken;
    }

    @Override
    public Long accessTokenExpiry() {
        return accessTokenExpiry;
    }

    @Override
    public String refreshToken() {
        return refreshToken;
    }

    @Override
    public Long refreshTokenExpiry() {
        return null;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getShopId() {
        return shopId;
    }

    public int[] getMerchantIds() {
        return merchantIds;
    }

    public int[] getShopIds() {
        return shopIds;
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
}
