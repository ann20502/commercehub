package com.commercehub.link.client.implementation;

import com.commercehub.link.client.AuthorizationCode;
import com.commercehub.link.client.AuthorizationCodeMapper;
import com.commercehub.link.qualifier.LinkDefault;

import javax.enterprise.context.Dependent;
import javax.ws.rs.core.MultivaluedMap;

@Dependent
@LinkDefault
public class AuthorizationCodeMapperDefault implements AuthorizationCodeMapper {

    @Override
    public AuthorizationCode map(MultivaluedMap<String,String> reqParam) {
        return new AuthorizationCodeDefault(reqParam.getFirst("code"));
    }

}
