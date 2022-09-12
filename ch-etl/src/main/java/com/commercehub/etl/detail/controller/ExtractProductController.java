package com.commercehub.etl.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.core.usecase.product.ItemETL;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/product/extract")
public class ExtractProductController {

    @Inject
    Logger log;

    @Inject
    ItemETL itemETL;

    @POST
    @Operation(summary = "Extract Product Update", description = "Extract product update")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Result> execute() {
        return Streams.toApiResult(itemETL.extractTransformLoad());
    }

}
