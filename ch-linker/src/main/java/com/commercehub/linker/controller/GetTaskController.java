package com.commercehub.linker.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.vm.SchedulerViewModel;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/task")
public class GetTaskController {

    @Inject
    SchedulerViewModel viewModel;

    @Path("/{taskGroup}/{shopId}/{type}")
    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get Task", description = "Get tasks by group + shop id + type")
    public Uni<Result> get(
            @NotEmpty @PathParam("taskGroup") String taskGroup,
            @NotEmpty @PathParam("shopId") String shopId,
            @NotEmpty @PathParam("type") String type
    ) {
        return Streams.toApiResult(viewModel.getTask(taskGroup, shopId, type));
    }

}
