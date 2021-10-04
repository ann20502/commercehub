package com.commercehub.rest.shopee.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetShopInfoOutput {

    private String request_id;
    private String shop_name;
    private String region;
    private String status;
    private boolean is_cb;
    private boolean is_cnsc;
    private long auth_time;
    private long expire_time;

    public GetShopInfoOutput() {}

    public GetShopInfoOutput(String request_id, String shop_name, String region, String status, boolean is_cb, boolean is_cnsc, long auth_time, long expire_time) {
        this.request_id = request_id;
        this.shop_name = shop_name;
        this.region = region;
        this.status = status;
        this.is_cb = is_cb;
        this.is_cnsc = is_cnsc;
        this.auth_time = auth_time;
        this.expire_time = expire_time;
    }

    public String getRequest_id() {
        return request_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getRegion() {
        return region;
    }

    public String getStatus() {
        return status;
    }

    public boolean isIs_cb() {
        return is_cb;
    }

    public boolean isIs_cnsc() {
        return is_cnsc;
    }

    public long getAuth_time() {
        return auth_time;
    }

    public long getExpire_time() {
        return expire_time;
    }

    @Override
    public String toString() {
        return "GetShopInfoOutput{" +
                "request_id='" + request_id + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", region='" + region + '\'' +
                ", status='" + status + '\'' +
                ", is_cb=" + is_cb +
                ", is_cnsc=" + is_cnsc +
                ", auth_time=" + auth_time +
                ", expire_time=" + expire_time +
                '}';
    }
}
