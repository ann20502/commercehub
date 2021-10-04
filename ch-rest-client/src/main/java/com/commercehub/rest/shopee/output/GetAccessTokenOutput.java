package com.commercehub.rest.shopee.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAccessTokenOutput {

    private String request_id;
    private String error;
    private String refresh_token;
    private String access_token;
    private int expire_in;
    private String message;
    private int[] merchant_id_list;
    private int[] shop_id_list;

    public GetAccessTokenOutput() {}

    public GetAccessTokenOutput(String request_id, String error, String refresh_token, String access_token, int expire_in, String message, int[] merchant_id_list, int[] shop_id_list) {
        this.request_id = request_id;
        this.error = error;
        this.refresh_token = refresh_token;
        this.access_token = access_token;
        this.expire_in = expire_in;
        this.message = message;
        this.merchant_id_list = merchant_id_list;
        this.shop_id_list = shop_id_list;
    }

    public String getRequest_id() {
        return request_id;
    }

    public String getError() {
        return error;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public int getExpire_in() {
        return expire_in;
    }

    public String getMessage() {
        return message;
    }

    public int[] getMerchant_id_list() {
        return merchant_id_list;
    }

    public int[] getShop_id_list() {
        return shop_id_list;
    }

    @Override
    public String toString() {
        return "GetAccessTokenOutput{" +
                "request_id='" + request_id + '\'' +
                ", error='" + error + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expire_in=" + expire_in +
                ", message='" + message + '\'' +
                ", merchant_id_list=" + Arrays.toString(merchant_id_list) +
                ", shop_id_list=" + Arrays.toString(shop_id_list) +
                '}';
    }

}
