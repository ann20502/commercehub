package com.commercehub.linker.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.vm.LinkingViewModel;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/linking")
public class GetLinkingController {

    @Inject
    LinkingViewModel vm;

    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get Linking", description = "Get all existing linking")
    public Uni<Result> execute() {
        return Streams.toApiResult(vm.getExistingLinking());
    }

    @Path("/{documentId}")
    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get Linking", description = "Get requested linking")
    public Uni<Result> getById(@NotEmpty @PathParam("documentId") String documentId) {
        return Streams.toApiResult(vm.getExistingLinking(documentId));
    }

}
