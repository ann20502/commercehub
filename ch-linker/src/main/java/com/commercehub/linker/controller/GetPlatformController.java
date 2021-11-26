package com.commercehub.linker.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.vm.PlatformViewModel;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/platform")
public class GetPlatformController {

    @Inject
    PlatformViewModel viewModel;

    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get Platform", description = "Get all supported platforms")
    public Uni<Result> execute() {
        return Streams.toApiResult(viewModel.getAll().map(Platform::new));
    }

    public static class Platform {
        public final String name;

        public Platform(String name) {
            this.name = name;
        }
    }

}
