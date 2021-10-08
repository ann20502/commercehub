package com.commercehub.etl.domain.entity;

public interface RenewTokenResult {

    String accessToken();
    int accessTokenExpireInSeconds();

    String refreshToken();
    int refreshTokenExpireInSeconds();

}
