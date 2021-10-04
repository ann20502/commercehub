package com.commercehub.link.client.implementation;

import com.commercehub.link.client.LinkClient;
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
public class LinkClientProducer {

    @Inject
    LinkRequestDataHolder dataHolder;

    @Inject
    @LinkDefault
    LinkClient linkClient;

    @Inject
    @Any
    Instance<LinkClient> linkClients;

    @Produces
    @LinkPreferred
    @RequestScoped
    LinkClient produce() {
        return linkClients
                .select(new LinkQualifierLiteral(dataHolder.getClient()))
                .stream().findFirst()
                .orElse(linkClient);
    }

}
