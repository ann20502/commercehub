package com.commercehub.etl.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.domain.entity.schduler.TimedTask;
import com.commercehub.etl.vm.SchedulerViewModel;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/scheduler/timed/execute")
public class ExecuteTimedTaskController {

    @Inject
    SchedulerViewModel viewModel;

    @POST
    @Operation(summary = "Execute timed task", description = "Execute 10 pending timed task")
    public Uni<Result> execute() {
        return Streams.toApiResult(viewModel.executeTimedTaskTrigger());
    }

}
