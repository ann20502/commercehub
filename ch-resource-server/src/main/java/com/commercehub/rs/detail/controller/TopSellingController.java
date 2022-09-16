package com.commercehub.rs.detail.controller;

import com.commercehub.api.common.ApiUtils;
import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.rs.core.usecase.boundary.TopSellingInputBoundary;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Path("/products")
public class TopSellingController {

    @Inject
    TopSellingInputBoundary inputBoundary;

    @Path("/top/{platform}/{shopId}")
    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Top Selling Products V2", description = "Get top selling products by date range version 2")
    public Uni<Result> get(
            @Parameter(description = "platform", required = true) @NotBlank @PathParam("platform") String platform,
            @Parameter(description = "shop id", required = true) @NotBlank @PathParam("shopId") String shopId,
            @Parameter(description = "dd-MMM-yyyy", required = true) @NotBlank @QueryParam("from") String from,
            @Parameter(description = "dd-MMM-yyyy", required = true) @NotBlank @QueryParam("to") String to,
            @Parameter(description = "any legit timezone", required = true) @NotBlank @QueryParam("zone") String zone
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApiUtils.INPUT_DATE_FORMAT);
        LocalDate dateFrom = LocalDate.parse(from, formatter);
        LocalDate dateTo = LocalDate.parse(to, formatter);

        return Streams.toApiResult(
                inputBoundary
                        .platform(platform)
                        .shopId(shopId)
                        .from(dateFrom)
                        .to(dateTo)
                        .zone(zone)
                        .execute()
        );
    }

}
