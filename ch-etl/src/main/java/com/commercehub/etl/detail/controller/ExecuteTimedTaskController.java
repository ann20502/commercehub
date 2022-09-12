package com.commercehub.etl.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskExecution;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/scheduler/timed/execute")
public class ExecuteTimedTaskController {

    @Inject
    TimedTaskExecution timedTaskExecution;

    @Blocking
    @POST
    @Operation(summary = "Execute timed task", description = "Execute pending timed task")
    public Uni<Result> execute() {
        return Streams.toApiResult(timedTaskExecution.execute());
    }

}
