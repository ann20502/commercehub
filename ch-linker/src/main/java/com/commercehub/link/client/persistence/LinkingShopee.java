package com.commercehub.link.client.persistence;

import java.util.Date;

public class LinkingShopee extends Linking {

    public final String shopId;
    public final String shopName;
    public final String shopStatus;
    public final String shopRegion;

    public LinkingShopee(String status, String platform, String accessToken, Date accessTokenExpiry, String refreshToken, Date refreshTokenExpiry, String shopId, String shopName, String shopStatus, String shopRegion) {
        super(status, platform, accessToken, accessTokenExpiry, refreshToken, refreshTokenExpiry);
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopStatus = shopStatus;
        this.shopRegion = shopRegion;
    }
}
