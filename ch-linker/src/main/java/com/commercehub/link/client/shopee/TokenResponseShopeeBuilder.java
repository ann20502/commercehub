package com.commercehub.link.client.shopee;

public class TokenResponseShopeeBuilder {

    private final long currentTimeMillis;
    private String requestId;
    private String shopId;
    private String shopName;
    private String shopStatus;
    private String shopRegion;
    private String accessToken;
    private Long accessTokenExpiry;
    private String refreshToken;
    private int[] merchantIds;
    private int[] shopIds;

    public TokenResponseShopeeBuilder() {
        currentTimeMillis = System.currentTimeMillis();
    }

    public TokenResponseShopeeBuilder setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public TokenResponseShopeeBuilder setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public TokenResponseShopeeBuilder setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public TokenResponseShopeeBuilder setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
        return this;
    }

    public TokenResponseShopeeBuilder setShopRegion(String shopRegion) {
        this.shopRegion = shopRegion;
        return this;
    }

    public TokenResponseShopeeBuilder setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public TokenResponseShopeeBuilder setAccessTokenExpireInSecond(Integer expireInSecond) {
        this.accessTokenExpiry = expireInSecond == null ? null : getExpiry(currentTimeMillis, expireInSecond);
        return this;
    }

    private long getExpiry(long currentTimeMillis, Integer expireInSecond) {
        final long BUFFER_10_SECONDS = 10000L;
        final long expireInMillis = expireInSecond * 1000L;
        return currentTimeMillis + expireInMillis - BUFFER_10_SECONDS;
    }

    public TokenResponseShopeeBuilder setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public TokenResponseShopeeBuilder setMerchantIds(int[] merchantIds) {
        this.merchantIds = merchantIds;
        return this;
    }

    public TokenResponseShopeeBuilder setShopIds(int[] shopIds) {
        this.shopIds = shopIds;
        return this;
    }

    public TokenResponseShopee createTokenResponseShopee() {
        return new TokenResponseShopee(requestId, shopId, shopName, shopStatus, shopRegion, accessToken, accessTokenExpiry, refreshToken, merchantIds, shopIds);
    }
}