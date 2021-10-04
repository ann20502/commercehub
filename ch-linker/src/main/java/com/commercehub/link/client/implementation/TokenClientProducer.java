package com.commercehub.link.client.implementation;

import com.commercehub.link.client.TokenClient;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifier;
import com.commercehub.link.qualifier.LinkQualifierLiteral;
import com.commercehub.link.request.LinkRequestDataHolder;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Produces;

@RequestScoped
public class TokenClientProducer {

    @Inject
    LinkRequestDataHolder dataHolder;

    @Inject
    @LinkQualifier("shopee")
    TokenClient tokenClient;

    @Inject
    @Any
    Instance<TokenClient> tokenClients;

    @Produces
    @LinkPreferred
    @RequestScoped
    public TokenClient produce() {
        return tokenClients
                .select(new LinkQualifierLiteral(dataHolder.getClient()))
                .stream().findFirst()
                .orElse(tokenClient);
    }

}
