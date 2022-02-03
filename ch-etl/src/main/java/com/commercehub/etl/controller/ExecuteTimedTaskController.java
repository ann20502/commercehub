package com.commercehub.etl.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.vm.SchedulerViewModel;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/scheduler/timed/execute")
public class ExecuteTimedTaskController {

    @Inject
    SchedulerViewModel viewModel;

    @POST
    @Operation(summary = "Execute timed task", description = "Execute pending timed task")
    public Uni<Result> execute() {
        return Streams.toApiResult(viewModel.executeTimedTaskTrigger());
    }

}
