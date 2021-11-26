package com.commercehub.rest.shopee.input;

import javax.ws.rs.QueryParam;

public class PublicApiCommonParam {

    @QueryParam("partner_id")
    public final int partnerId;

    @QueryParam("partner_secret")
    public final String partnerSecret;

    public PublicApiCommonParam(int partnerId, String partnerSecret) {
        this.partnerId = partnerId;
        this.partnerSecret = partnerSecret;
    }

}
