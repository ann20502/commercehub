package com.commercehub.etl.controller.shopee;

import com.commercehub.etl.domain.usecase.product.BoostItem;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/product/boost/shopee")
public class BoostProductShopeeController {

    @Inject
    BoostItem boostItem;

    @Inject
    Logger log;

    @Blocking
    @POST
    @Operation(summary = "Boost Shopee Product", description = "Boost Shopee product exposure based on configured list")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Boolean> execute() {
        return boostItem.boost();
    }

}
