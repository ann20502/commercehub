package com.commercehub.link.client.implementation;

import com.commercehub.link.client.AuthorizationCodeHandler;
import com.commercehub.link.qualifier.LinkDefault;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifierLiteral;
import com.commercehub.link.request.LinkRequestDataHolder;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Produces;

public class AuthorizationCodeHandlerProducer {

    @Inject
    LinkRequestDataHolder dataHolder;

    @Inject
    @LinkDefault
    AuthorizationCodeHandlerDefault authorizationCodeHandlerDefault;

    @Inject
    @Any
    Instance<AuthorizationCodeHandler> authorizationCodeHandlers;

    @Produces
    @LinkPreferred
    @RequestScoped
    public AuthorizationCodeHandler produce() {
        return authorizationCodeHandlers
                .select(new LinkQualifierLiteral(dataHolder.getClient()))
                .stream().findFirst()
                .orElse(authorizationCodeHandlerDefault);
    }

}
