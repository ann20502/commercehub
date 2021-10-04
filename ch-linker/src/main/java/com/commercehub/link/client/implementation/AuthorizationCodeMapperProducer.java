package com.commercehub.link.client.implementation;

import com.commercehub.link.client.AuthorizationCodeMapper;
import com.commercehub.link.qualifier.LinkDefault;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifierLiteral;
import com.commercehub.link.request.LinkRequestDataHolder;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Produces;

@RequestScoped
public class AuthorizationCodeMapperProducer {

    @Inject
    LinkRequestDataHolder dataHolder;

    @Inject
    @LinkDefault
    AuthorizationCodeMapperDefault authorizationCodeMapperDefault;

    @Inject
    @Any
    Instance<AuthorizationCodeMapper> authorizationCodeMappers;

    @Produces
    @LinkPreferred
    @RequestScoped
    public AuthorizationCodeMapper produce() {
        return authorizationCodeMappers
                .select(new LinkQualifierLiteral(dataHolder.getClient()))
                .stream().findFirst()
                .orElse(authorizationCodeMapperDefault);
    }

}
