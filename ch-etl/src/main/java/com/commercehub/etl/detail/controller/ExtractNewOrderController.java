package com.commercehub.etl.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.core.usecase.order.OrderETL;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/order/extract")
public class ExtractNewOrderController {

    @Inject
    Logger log;

    @Inject
    OrderETL orderETL;

    @Blocking
    @POST
    @Operation(summary = "Extract New Order", description = "Extract new order")
    public Uni<Result> execute(
            @NotEmpty @QueryParam("documentId") String documentId,
            @NotEmpty @QueryParam("platform") String platform
    ) {
        return Streams.toApiResult(orderETL.extractTransformLoad(documentId, platform));
    }

}
