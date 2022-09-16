package com.commercehub.linker.detail.usecase;

import com.commercehub.link.client.LinkClientConfigurations;
import com.commercehub.linker.core.usecase.GetSupportedPlatform;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GetSupportedPlatformInteractor implements GetSupportedPlatform {

    @Inject
    LinkClientConfigurations configurations;

    @Override
    public Multi<String> execute() {
        return Multi.createFrom().items(configurations.clients().keySet().stream());
    }

}
