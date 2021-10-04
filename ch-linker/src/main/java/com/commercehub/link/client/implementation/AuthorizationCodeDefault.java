package com.commercehub.link.client.implementation;

import com.commercehub.link.client.AuthorizationCode;

public class AuthorizationCodeDefault implements AuthorizationCode {

    private final String code;

    public AuthorizationCodeDefault(String code) {
        this.code = code;
    }

    @Override
    public String authorizationCode() {
        return code;
    }

}
