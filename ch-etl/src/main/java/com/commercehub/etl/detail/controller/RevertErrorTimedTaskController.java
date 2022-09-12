package com.commercehub.etl.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskReversion;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/scheduler/timed/fix")
public class RevertErrorTimedTaskController {

    @Inject
    TimedTaskReversion timedTaskReversion;

    @Blocking
    @POST
    @Operation(summary = "Revert error timed task", description = "Revert error timed task to pending status")
    public Uni<Result> execute() {
        return Streams.toApiResult(timedTaskReversion.revertError());
    }

}
