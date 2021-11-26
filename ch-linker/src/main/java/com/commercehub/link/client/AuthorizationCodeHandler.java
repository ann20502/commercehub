package com.commercehub.link.client;

import com.commercehub.link.client.repository.LinkingRequest;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.core.MultivaluedMap;

public interface AuthorizationCodeHandler {

    Uni<TokenResponse> handle(LinkingRequest request, MultivaluedMap<String, String> reqParam);

}
