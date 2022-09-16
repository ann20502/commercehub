package com.commercehub.rs.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.rs.core.usecase.GetShopPerformance;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/shop")
public class ShopPerformanceController {

    @Inject
    GetShopPerformance getShopPerformance;

    @Path("/performance/{platform}/{shopId}")
    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get shop performance V2", description = "Get latest shop performance version 2")
    public Uni<Result> get(
            @Parameter(description = "platform", required = true) @NotBlank @PathParam("platform") String platform,
            @Parameter(description = "shop id", required = true) @NotBlank @PathParam("shopId") String shopId
    ) {
        return Streams.toApiResult(getShopPerformance.execute(platform, shopId));
    }

}
