package com.commercehub.link.client.implementation;

import com.commercehub.link.client.TokenResponseHandler;
import com.commercehub.link.qualifier.LinkDefault;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifierLiteral;
import com.commercehub.link.request.LinkRequestDataHolder;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Produces;

public class TokenResponseHandlerProducer {

    @Inject
    LinkRequestDataHolder dataHolder;

    @Inject
    @LinkDefault
    TokenResponseHandler tokenResponseHandler;

    @Inject
    @Any
    Instance<TokenResponseHandler> tokenResponseHandlers;

    @Produces
    @LinkPreferred
    @RequestScoped
    public TokenResponseHandler produce() {
        return tokenResponseHandlers
                .select(new LinkQualifierLiteral(dataHolder.getClient()))
                .stream().findFirst()
                .orElse(tokenResponseHandler);
    }

}
