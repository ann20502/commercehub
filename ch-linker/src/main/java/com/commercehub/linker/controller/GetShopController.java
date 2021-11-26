package com.commercehub.linker.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.vm.LinkingViewModel;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/shop")
public class GetShopController {

    @Inject
    LinkingViewModel viewModel;

    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get Shop", description = "Get all active shops")
    public Uni<Result> getAll() {
        return Streams.toApiResult(viewModel.getShop());
    }

}
