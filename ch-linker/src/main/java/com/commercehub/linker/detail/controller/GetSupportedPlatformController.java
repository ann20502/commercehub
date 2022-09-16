package com.commercehub.linker.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.core.usecase.GetSupportedPlatform;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/platform")
public class GetSupportedPlatformController {

    @Inject
    GetSupportedPlatform getSupportedPlatform;

    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get Supported Platform", description = "Get all supported platforms")
    public Uni<Result> execute() {
        return Streams.toApiResult(getSupportedPlatform.execute());
    }

}
