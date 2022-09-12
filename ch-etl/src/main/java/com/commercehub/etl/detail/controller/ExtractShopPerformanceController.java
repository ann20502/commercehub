package com.commercehub.etl.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.core.usecase.shop.PerformanceETL;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/performance/extract")
public class ExtractShopPerformanceController {

    @Inject
    Logger log;

    @Inject
    PerformanceETL performanceETL;

    @POST
    @Operation(summary = "Extract Shop Performance", description = "Extract shop performance")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Result> execute() {
        return Streams.toApiResult(performanceETL.extractTransformLoad());
    }

}
