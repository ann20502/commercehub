package com.commercehub.link.client;

public interface TokenResponse {

    String accessToken();
    Long accessTokenExpiry();

    String refreshToken();
    Long refreshTokenExpiry();

}
