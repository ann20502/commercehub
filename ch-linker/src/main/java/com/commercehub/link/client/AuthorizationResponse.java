package com.commercehub.link.client;

public interface AuthorizationResponse {

    String platform();

    String accessToken();
    Long accessTokenExpiry();

    String refreshToken();
    Long refreshTokenExpiry();

}
