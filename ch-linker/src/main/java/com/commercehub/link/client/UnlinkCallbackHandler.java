package com.commercehub.link.client;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.core.MultivaluedMap;

public interface UnlinkCallbackHandler {

    Uni<Boolean> handle(MultivaluedMap<String, String> reqParam);

}
