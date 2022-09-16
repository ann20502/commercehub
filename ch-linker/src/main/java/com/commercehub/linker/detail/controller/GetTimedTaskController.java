package com.commercehub.linker.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.core.usecase.scheduler.GetTask;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/task")
public class GetTimedTaskController {

    @Inject
    GetTask getTask;

    @Blocking
    @Path("/{taskGroup}/{shopId}/{type}")
    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get Task", description = "Get tasks by task group name + shop id + type")
    public Uni<Result> get(
            @Parameter(description = "task group name")@NotEmpty @PathParam("taskGroup") String taskGroup,
            @Parameter(description = "shop id") @NotEmpty @PathParam("shopId") String shopId,
            @Parameter(description = GetTask.REGEX_ALL_TYPE) @Pattern(regexp = GetTask.REGEX_ALL_TYPE) @PathParam("type") String type
    ) {
        return Streams.toApiResult(getTask.execute(taskGroup, shopId, type));
    }

}
