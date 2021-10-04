package com.commercehub.link.client.implementation;

import com.commercehub.link.client.*;
import com.commercehub.link.qualifier.LinkDefault;
import com.commercehub.link.qualifier.LinkPreferred;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

@Dependent
@LinkDefault
public class LinkClientDefault implements LinkClient {

    public static final String NAME = "default";

    @Inject
    @LinkPreferred
    AuthorizationRedirect authorizationRedirect;

    @Inject
    @LinkPreferred
    AuthorizationCodeHandler authorizationCodeHandler;

    @Inject
    @LinkPreferred
    TokenResponseHandler tokenResponseHandler;

    @Override
    public AuthorizationRedirect authorizationRedirect() {
        return authorizationRedirect;
    }

    @Override
    public Uni<AuthorizationResponse> onCallback(MultivaluedMap<String,String> requestParam) {
        Uni<TokenResponse> tokenResponse = authorizationCodeHandler.handle(requestParam);
        return tokenResponseHandler.handle(tokenResponse);
    }

}
