package com.commercehub.linker.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.vm.SchedulerViewModel;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/taskGroup")
public class GetTaskGroupController {

    @Inject
    SchedulerViewModel viewModel;

    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get Task Group", description = "Get all task groups")
    public Uni<Result> get() {
        return Streams.toApiResult(viewModel.getTaskGroup());
    }

}
