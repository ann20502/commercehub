package com.commercehub.etl.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.vm.SchedulerViewModel;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/scheduler/timed/fix")
public class RevertErrorTimedTaskController {

    @Inject
    SchedulerViewModel viewModel;

    @POST
    @Operation(summary = "Revert error timed task", description = "Revert error timed task to pending status")
    public Uni<Result> execute() {
        return Streams.toApiResult(viewModel.revertErrorTimeTask());
    }

}
