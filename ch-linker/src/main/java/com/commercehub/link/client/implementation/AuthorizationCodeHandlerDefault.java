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
public class AuthorizationCodeHandlerDefault implements AuthorizationCodeHandler {

    @Inject
    @LinkPreferred
    AuthorizationCodeMapper authorizationCodeMapper;

    @Inject
    @LinkPreferred
    TokenClient tokenClient;

    @Override
    public Uni<TokenResponse> handle(MultivaluedMap<String,String> reqParam) {
        AuthorizationCode code = authorizationCodeMapper.map(reqParam);
        return tokenClient.sendRequest(code);
    }

}
