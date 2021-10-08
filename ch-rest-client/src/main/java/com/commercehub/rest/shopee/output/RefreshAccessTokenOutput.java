package com.commercehub.rest.shopee.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RefreshAccessTokenOutput {

    private String request_id;
    private String error;
    private String refresh_token;
    private String access_token;
    private int expire_in;
    private int partner_id;
    private int shop_id;
    private int merchant_id;

    public RefreshAccessTokenOutput() {}

    public RefreshAccessTokenOutput(String request_id, String error, String refresh_token, String access_token, int expire_in, int partner_id, int shop_id, int merchant_id) {
        this.request_id = request_id;
        this.error = error;
        this.refresh_token = refresh_token;
        this.access_token = access_token;
        this.expire_in = expire_in;
        this.partner_id = partner_id;
        this.shop_id = shop_id;
        this.merchant_id = merchant_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpire_in() {
        return expire_in;
    }

    public void setExpire_in(int expire_in) {
        this.expire_in = expire_in;
    }

    public int getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(int partner_id) {
        this.partner_id = partner_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

}
