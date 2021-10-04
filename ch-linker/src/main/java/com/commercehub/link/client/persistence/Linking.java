package com.commercehub.link.client.persistence;

import java.util.Date;

public class Linking {

    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_INACTIVE = "I";

    public final String status;
    public final String platform;
    public final String accessToken;
    public final Date accessTokenExpiry;
    public final String refreshToken;
    public final Date refreshTokenExpiry;

    public Linking(String status, String platform, String accessToken, Date accessTokenExpiry, String refreshToken, Date refreshTokenExpiry) {
        this.status = status;
        this.platform = platform;
        this.accessToken = accessToken;
        this.accessTokenExpiry = accessTokenExpiry;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiry = refreshTokenExpiry;
    }
}
