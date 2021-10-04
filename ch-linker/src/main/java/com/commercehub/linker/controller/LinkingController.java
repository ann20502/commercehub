package com.commercehub.linker.controller;

import com.commercehub.linker.domain.entity.Linking;
import com.commercehub.linker.vm.LinkingViewModel;
import io.smallrye.mutiny.Multi;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriInfo;

@Path("/linking")
public class LinkingController {

    @Inject
    LinkingViewModel vm;

    @Inject
    UriInfo uriInfo;

    @GET
    public Multi<Linking> execute() {
        return vm.getAll().onItem().transformToMulti(linking -> Multi.createFrom().iterable(linking))
            .map(linking -> {
                final String pathLogo = linking.getLogo();
                linking.setLogo(uriInfo.getBaseUri().toString() + pathLogo);
                return linking;
            });
    }

}
