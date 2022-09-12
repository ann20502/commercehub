package com.commercehub.etl.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.core.usecase.order.OrderUpdateETL;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/order/update")
public class ExtractOrderUpdateController {

    @Inject
    Logger log;

    @Inject
    OrderUpdateETL orderUpdateETL;

    @Blocking
    @POST
    @Operation(summary = "Extract Order Update", description = "Extract order update")
    public Uni<Result> execute(
            @NotEmpty @QueryParam("documentId") String documentId,
            @NotEmpty @QueryParam("platform") String platform
    ) {
        return Streams.toApiResult(orderUpdateETL.extractTransformLoad(documentId, platform));
    }

}
