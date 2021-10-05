package com.commercehub.link.client.implementation;

import com.commercehub.link.client.UnlinkAuthorizationRedirect;
import com.commercehub.link.client.UnlinkCallbackHandler;
import com.commercehub.link.qualifier.LinkDefault;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifierLiteral;
import com.commercehub.link.request.LinkRequestDataHolder;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class UnlinkCallbackHandlerProducer {

    @Inject
    LinkRequestDataHolder dataHolder;

    @Inject
    @LinkDefault
    UnlinkCallbackHandler unlinkCallbackHandler;

    @Inject
    @Any
    Instance<UnlinkCallbackHandler> unlinkCallbackHandlers;

    @Produces
    @LinkPreferred
    @RequestScoped
    public UnlinkCallbackHandler produce() {
        return unlinkCallbackHandlers
                .select(new LinkQualifierLiteral(dataHolder.getClient()))
                .stream().findFirst()
                .orElse(unlinkCallbackHandler);
    }

}
