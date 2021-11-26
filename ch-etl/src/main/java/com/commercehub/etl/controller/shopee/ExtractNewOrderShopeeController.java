package com.commercehub.etl.controller.shopee;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.vm.OrderViewModel;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/order/extract/shopee")
public class ExtractNewOrderShopeeController {

    @Inject
    OrderViewModel viewModel;

    @POST
    @Operation(summary = "Extract New Order", description = "Extract new order from Shopee")
    public Uni<Result> execute(@NotEmpty @QueryParam("documentId") String documentId) {
        return Streams.toApiResult(viewModel.extractNewOrderShopee(documentId));
    }

}
