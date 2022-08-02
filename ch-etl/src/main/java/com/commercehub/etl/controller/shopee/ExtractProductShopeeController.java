package com.commercehub.etl.controller.shopee;

import com.commercehub.etl.domain.usecase.product.ExtractItemUpdate;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/product/update/shopee")
public class ExtractProductShopeeController {

    @Inject
    ExtractItemUpdate extractItemUpdate;

    @Inject
    Logger log;

    @POST
    @Operation(summary = "Extract Product Update", description = "Extract product update from Shopee")
    public Uni<Boolean> execute() {
        return extractItemUpdate.extract();
    }

}
