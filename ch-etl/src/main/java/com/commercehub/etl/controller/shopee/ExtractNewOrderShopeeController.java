package com.commercehub.etl.controller.shopee;

import com.commercehub.etl.vm.OrderViewModel;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/order/extract/shopee")
public class ExtractNewOrderShopeeController {

    @Inject
    Logger log;

    @Inject
    OrderViewModel viewModel;

    @POST
    @Operation(summary = "Extract New Order", description = "Extract new order from Shopee")
    public Uni<Integer> execute(@NotEmpty @QueryParam("documentId") String documentId) {
        return viewModel.extractNewOrderShopee(documentId)
                .map(orders -> {
                    log.info("Extracted [" + orders.size() + "] orders");
                    return orders.size();
                });
    }

}
