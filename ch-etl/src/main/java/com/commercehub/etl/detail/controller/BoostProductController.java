package com.commercehub.etl.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.core.usecase.product.ItemBoost;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/product/boost")
public class BoostProductController {

    @Inject
    ItemBoost itemBoost;

    @Inject
    Logger log;

    @Blocking
    @POST
    @Operation(summary = "Boost Product", description = "Boost product exposure based on configured list")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Result> execute() {
        return Streams.toApiResult(itemBoost.boost());
    }

}
