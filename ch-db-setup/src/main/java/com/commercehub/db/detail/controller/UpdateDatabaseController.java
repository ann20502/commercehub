package com.commercehub.db.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.db.core.usecase.DatabaseUpdation;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/database/update")
public class UpdateDatabaseController {

    @Inject
    DatabaseUpdation databaseUpdation;

    @Blocking
    @POST
    @Operation(summary = "Update dataset schema", description = "Update dataset schema for all ACTIVE linking")
    public Uni<Result> execute() {
        return Streams.toApiResult(databaseUpdation.update());
    }

}
