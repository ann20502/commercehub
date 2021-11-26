package com.commercehub.etl.domain.service;

public interface RenewTokenResult {

    String accessToken();
    int accessTokenExpireInSeconds();

    String refreshToken();
    int refreshTokenExpireInSeconds();

}
