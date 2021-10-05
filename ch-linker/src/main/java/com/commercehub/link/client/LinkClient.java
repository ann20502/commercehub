package com.commercehub.link.client;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.core.MultivaluedMap;

public interface LinkClient {

    AuthorizationRedirect authorizationRedirect();

    Uni<AuthorizationResponse> onCallback(MultivaluedMap<String,String> requestParam);

    default UnlinkAuthorizationRedirect unlinkAuthorizationRedirect() {
        throw new UnsupportedOperationException("Unlink function unsupported");
    }

    default Uni<Boolean> unlinkOnCallback(MultivaluedMap<String,String> requestParam) {
        throw new UnsupportedOperationException("unlink function unsupported");
    }

}
