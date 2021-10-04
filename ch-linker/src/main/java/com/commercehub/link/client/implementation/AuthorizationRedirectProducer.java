package com.commercehub.link.client.implementation;

import com.commercehub.link.client.AuthorizationRedirect;
import com.commercehub.link.qualifier.LinkDefault;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifierLiteral;
import com.commercehub.link.request.LinkRequestDataHolder;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@RequestScoped
public class AuthorizationRedirectProducer {

    @Inject
    LinkRequestDataHolder dataHolder;

    @Inject
    @LinkDefault
    AuthorizationRedirect authorizationRedirect;

    @Inject
    @Any
    Instance<AuthorizationRedirect> authorizationRedirects;

    @Produces
    @LinkPreferred
    @RequestScoped
    public AuthorizationRedirect produce() {
        return authorizationRedirects
                .select(new LinkQualifierLiteral(dataHolder.getClient()))
                .stream().findFirst()
                .orElse(authorizationRedirect);
    }

}
