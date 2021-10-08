package com.commercehub.etl.controller;

import com.commercehub.etl.domain.entity.Linking;
import com.commercehub.etl.vm.LinkingViewModel;
import io.smallrye.mutiny.Multi;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/platform/token/renew")
public class RenewAccessTokenController {

    @Inject
    LinkingViewModel viewModel;

    @GET
    public Multi<Linking> execute() {
        return viewModel.renewTokenAndSave();
    }

}
