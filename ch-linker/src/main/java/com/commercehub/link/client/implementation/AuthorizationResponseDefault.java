package com.commercehub.link.client.implementation;

import com.commercehub.link.client.AuthorizationResponse;

public class AuthorizationResponseDefault implements AuthorizationResponse {

    private final String platform;
    private final String accessToken;
    private final Long accessTokenExpiry;
    private final String refreshToken;
    private final Long refreshTokenExpiry;

    public AuthorizationResponseDefault(String platform, String accessToken, Long accessTokenExpiry, String refreshToken, Long refreshTokenExpiry) {
        this.platform = platform;
        this.accessToken = accessToken;
        this.accessTokenExpiry = accessTokenExpiry;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiry = refreshTokenExpiry;
    }

    @Override
    public String platform() {
        return platform;
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
        return refreshTokenExpiry;
    }

    @Override
    public String toString() {
        return "AuthorizationResponseDefault{" +
                "platform='" + platform + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", accessTokenExpiry=" + accessTokenExpiry +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenExpiry=" + refreshTokenExpiry +
                '}';
    }

}
