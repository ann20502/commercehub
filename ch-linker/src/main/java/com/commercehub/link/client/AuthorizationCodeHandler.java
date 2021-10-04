package com.commercehub.link.client;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.core.MultivaluedMap;

public interface AuthorizationCodeHandler {

    Uni<TokenResponse> handle(MultivaluedMap<String, String> reqParam);

}
