package com.commercehub.link.client.implementation;

import com.commercehub.link.client.AuthorizationRedirect;
import com.commercehub.link.client.UnlinkAuthorizationRedirect;
import com.commercehub.link.qualifier.LinkDefault;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifier;
import com.commercehub.link.qualifier.LinkQualifierLiteral;
import com.commercehub.link.request.LinkRequestDataHolder;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class UnlinkAuthorizationRedirectProducer {

    @Inject
    LinkRequestDataHolder dataHolder;

    @Inject
    @LinkDefault
    UnlinkAuthorizationRedirect authorizationRedirect;

    @Inject
    @Any
    Instance<UnlinkAuthorizationRedirect> authorizationRedirects;

    @Produces
    @LinkPreferred
    @RequestScoped
    public UnlinkAuthorizationRedirect produce() {
        return authorizationRedirects
                .select(new LinkQualifierLiteral(dataHolder.getClient()))
                .stream().findFirst()
                .orElse(authorizationRedirect);
    }

}
