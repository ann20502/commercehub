package com.commercehub.link.client;

import javax.ws.rs.core.MultivaluedMap;

public interface AuthorizationCodeMapper {

    AuthorizationCode map(MultivaluedMap<String,String> reqParam);

}
