package com.commercehub.rest.shopee.input;

public class RefreshAccessTokenInput {

    public final String refresh_token;
    public final int partner_id;
    public final int shop_id;

    public RefreshAccessTokenInput(String refresh_token, int partner_id, int shop_id) {
        this.refresh_token = refresh_token;
        this.partner_id = partner_id;
        this.shop_id = shop_id;
    }

}
