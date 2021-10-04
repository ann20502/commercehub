package com.commercehub.rest.shopee;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/*
    With current implementation, library user is allowed to set the configurations in either:
    1. Set the field through application.properties / application.yaml
    2. Replace this implementation with @Specializes or @Alternative (Will @Alternative work?)

    Question: Is there a more convenient way for user to override in CDI ?
 */
@Dependent
@Default
public class ShopeeConfigurationDefault implements ShopeeConfiguration {

    @Inject
    @ConfigProperty(name = "com.commercehub.link.clients.shopee.client-id")
    String clientId;

    @Inject
    @ConfigProperty(name = "com.commercehub.link.clients.shopee.client-secret")
    String clientSecret;

    @Override
    public String clientId() {
        return clientId;
    }

    @Override
    public String clientSecret() {
        return clientSecret;
    }

}
