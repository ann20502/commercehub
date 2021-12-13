package com.commercehub.etl.controller.shopee;

import com.commercehub.etl.vm.OrderViewModel;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/order/update/shopee")
public class ExtractOrderUpdateShopeeController {

    @Inject
    Logger log;

    @Inject
    OrderViewModel viewModel;

    @Blocking
    @POST
    @Operation(summary = "Extract Order Update", description = "Extract order update from Shopee")
    public Uni<Integer> execute(@NotEmpty @QueryParam("documentId") String documentId) {
        return viewModel.extractOrderUpdateShopee(documentId)
                .map(orders -> {
                    log.info("Extracted [" + orders.size() + "] order updates");
                    return orders.size();
                });
    }

}
