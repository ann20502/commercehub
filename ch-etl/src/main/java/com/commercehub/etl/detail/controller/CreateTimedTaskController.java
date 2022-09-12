package com.commercehub.etl.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskCreation;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/scheduler/timed/create")
public class CreateTimedTaskController {

    @Inject
    TimedTaskCreation timedTaskCreation;

    @Blocking
    @POST
    @Operation(summary = "Create timed task", description = "Create pending timed task")
    public Uni<Result> execute() {
        return Streams.toApiResult(timedTaskCreation.create());
    }

}
