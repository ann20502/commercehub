package com.commercehub.link.client.implementation;

import com.commercehub.link.client.*;
import com.commercehub.link.qualifier.LinkDefault;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.client.repository.LinkingRequest;
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

    @Inject
    @LinkPreferred
    UnlinkAuthorizationRedirect unlinkAuthorizationRedirect;

    @Inject
    @LinkPreferred
    UnlinkCallbackHandler unlinkCallbackHandler;

    @Override
    public AuthorizationRedirect authorizationRedirect() {
        return authorizationRedirect;
    }

    @Override
    public Uni<AuthorizationResponse> onCallback(LinkingRequest request, MultivaluedMap<String,String> requestParam) {
        Uni<TokenResponse> tokenResponse = authorizationCodeHandler.handle(request, requestParam);
        return tokenResponseHandler.handle(request, tokenResponse);
    }

    @Override
    public UnlinkAuthorizationRedirect unlinkAuthorizationRedirect() {
        return unlinkAuthorizationRedirect;
    }

    @Override
    public Uni<Boolean> unlinkOnCallback(String documentId, MultivaluedMap<String, String> requestParam) {
        return unlinkCallbackHandler.handle(documentId, requestParam);
    }

}
