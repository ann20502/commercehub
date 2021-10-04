package com.commercehub.link.client.implementation;

import com.commercehub.link.client.LinkClientConfiguration;
import com.commercehub.link.client.LinkClientConfigurations;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.request.LinkRequestDataHolder;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@RequestScoped
public class LinkClientConfigurationProducer {

    @Inject
    LinkRequestDataHolder dataHolder;

    @Inject
    LinkClientConfigurations configurations;

    @Produces
    @LinkPreferred
    @RequestScoped
    LinkClientConfiguration produce() {
        return configurations.clients().get(dataHolder.getClient());
    }

}
