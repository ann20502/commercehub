package com.commercehub.etl.domain.service;

public class RenewTokenResultShopee implements RenewTokenResult {

    public final String accessToken;
    public final int accessTokenExpireInSeconds;
    public final String refreshToken;
    public final int refreshTokenExpireInSeconds;

    public RenewTokenResultShopee(String accessToken, int accessTokenExpireInSeconds, String refreshToken, int refreshTokenExpireInSeconds) {
        this.accessToken = accessToken;
        this.accessTokenExpireInSeconds = accessTokenExpireInSeconds;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireInSeconds = refreshTokenExpireInSeconds;
    }

    @Override
    public String accessToken() {
        return accessToken;
    }

    @Override
    public int accessTokenExpireInSeconds() {
        return accessTokenExpireInSeconds;
    }

    @Override
    public String refreshToken() {
        return refreshToken;
    }

    @Override
    public int refreshTokenExpireInSeconds() {
        return refreshTokenExpireInSeconds;
    }

}
