package com.commercehub.link.client.shopee;

import com.commercehub.link.client.AuthorizationCode;
import com.commercehub.link.client.AuthorizationCodeMapper;
import com.commercehub.link.qualifier.LinkQualifier;

import javax.enterprise.context.Dependent;
import javax.ws.rs.core.MultivaluedMap;

@Dependent
@LinkQualifier("shopee")
public class AuthorizationCodeMapperShopee implements AuthorizationCodeMapper {

    @Override
    public AuthorizationCode map(MultivaluedMap<String, String> reqParam) {
        return new AuthorizationCodeShopee(
                reqParam.getFirst("code"),
                reqParam.getFirst("shop_id"),
                reqParam.getFirst("main_account_id")
        );
    }

}
