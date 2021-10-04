package com.commercehub.linker.domain.usecase;

import com.commercehub.link.client.LinkClientConfigurations;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GetAvailablePlatformName {

    @Inject
    LinkClientConfigurations configurations;

    public Multi<String> getAll() {
        return Multi.createFrom().items(configurations.clients().keySet().stream());
    }

}
