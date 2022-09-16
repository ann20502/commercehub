package com.commercehub.rs.detail.controller;

import com.commercehub.api.common.ApiUtils;
import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.rs.core.usecase.GetSale;
import com.commercehub.rs.core.usecase.boundary.GetSaleInputBoundary;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Path("/sales")
public class GetSaleController {

    @Inject
    GetSaleInputBoundary inputBoundary;

    @Path("/{groupBy}/{platform}/{shopId}")
    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get sales", description = "Get sales aggregated by date or month")
    public Uni<Result> get(
            @Parameter(description = GetSale.REGEX_GROUP) @Pattern(regexp = GetSale.REGEX_GROUP) @PathParam("groupBy") String groupBy,
            @Parameter(description = "platform", required = true) @NotBlank @PathParam("platform") String platform,
            @Parameter(description = "shop id", required = true) @NotBlank @PathParam("shopId") String shopId,
            @Parameter(description = "dd-MMM-yyyy", required = true) @NotBlank @QueryParam("from") String from,
            @Parameter(description = "dd-MMM-yyyy", required = true) @NotBlank @QueryParam("to") String to,
            @Parameter(description = "Any legit timezone", required = true) @NotBlank @QueryParam("zone") String zone
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApiUtils.INPUT_DATE_FORMAT);
        LocalDate dateFrom = LocalDate.parse(from, formatter);
        LocalDate dateTo = LocalDate.parse(to, formatter);

        return Streams.toApiResult(
                inputBoundary
                        .groupBy(groupBy)
                        .platform(platform)
                        .shopId(shopId)
                        .from(dateFrom)
                        .to(dateTo)
                        .zone(zone)
                        .execute()
        );
    }

//    @Path("/{groupBy}/{shopId}")
//    @GET
//    @Operation(description = "Get sales aggregated by daily or monthly")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Uni<Result> get(
//            @Valid @BeanParam Input input
//    ) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApiUtils.INPUT_DATE_FORMAT);
//        LocalDate dateFrom = LocalDate.parse(input.getFrom(), formatter);
//        LocalDate dateTo = LocalDate.parse(input.getTo(), formatter);
//        return Streams.toApiResult(getSalesByShop.get(input.getGroupBy(), input.getShopId(), dateFrom, dateTo, input.getZone()));
//    }

}
