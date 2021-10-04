package com.commercehub.link.client.shopee;

import com.commercehub.link.client.AuthorizationCode;

public class AuthorizationCodeShopee implements AuthorizationCode {

    private final String code;
    private final String shopId;
    private final String mainAccountId;

    public AuthorizationCodeShopee(String code, String shopId, String mainAccountId) {
        this.code = code;
        this.shopId = shopId;
        this.mainAccountId = mainAccountId;
    }

    @Override
    public String authorizationCode() {
        return code;
    }

    public String shopId() {
        return shopId;
    }

    public String mainAccountId() {
        return mainAccountId;
    }

    @Override
    public String toString() {
        return "AuthorizationCodeShopee{" +
                "code='" + code + '\'' +
                ", shopId='" + shopId + '\'' +
                ", mainAccountId='" + mainAccountId + '\'' +
                '}';
    }

}
