package com.commercehub.rest.shopee.input;

import javax.ws.rs.QueryParam;

public class ShopApiCommonParam {

    @QueryParam("partner_id")
    public final int partnerId;

    @QueryParam("partner_secret")
    public final String partnerSecret;

    @QueryParam("access_token")
    public final String accessToken;

    @QueryParam("shop_id")
    public final int shopId;

    public ShopApiCommonParam(int partnerId, String partnerSecret, String accessToken, int shopId) {
        this.partnerId = partnerId;
        this.partnerSecret = partnerSecret;
        this.accessToken = accessToken;
        this.shopId = shopId;
    }

}
