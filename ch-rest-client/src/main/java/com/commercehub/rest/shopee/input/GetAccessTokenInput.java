package com.commercehub.rest.shopee.input;

public class GetAccessTokenInput {

    public final String code;
    public final int partner_id;
    public final int shop_id;

    public GetAccessTokenInput(String code, int partner_id, int shop_id) {
        this.code = code;
        this.partner_id = partner_id;
        this.shop_id = shop_id;
    }

    @Override
    public String toString() {
        return "GetAccessTokenInput{" +
                "code='" + code + '\'' +
                ", partner_id=" + partner_id +
                ", shop_id=" + shop_id +
                '}';
    }
}
