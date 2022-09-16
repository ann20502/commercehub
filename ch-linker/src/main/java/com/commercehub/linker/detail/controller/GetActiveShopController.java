package com.commercehub.linker.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.core.entity.Linking;
import com.commercehub.linker.core.usecase.GetShop;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/shop")
public class GetActiveShopController {

    @Inject
    GetShop getShop;

    @Blocking
    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get Shop", description = "Get all active shops")
    public Uni<Result> getAll() {
        return Streams.toApiResult(getShop.execute(Linking.STATUS_ACTIVE));
    }

}
