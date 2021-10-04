package com.commercehub.link.client;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.core.MultivaluedMap;

public interface LinkClient {

    AuthorizationRedirect authorizationRedirect();

    Uni<AuthorizationResponse> onCallback(MultivaluedMap<String,String> requestParam);

}
