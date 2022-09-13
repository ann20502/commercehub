package com.commercehub.db.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.db.core.usecase.DatabaseCreation;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/database/create")
public class CreateDatabaseController {

    @Inject
    DatabaseCreation databaseCreation;

    @Blocking
    @POST
    @Operation(summary = "Create database schema", description = "Create database for linking that has not been SETUP")
    public Uni<Result> execute() {
        return Streams.toApiResult(databaseCreation.create());
    }

}
