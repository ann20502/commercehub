package com.commercehub.linker.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.core.usecase.scheduler.GetTaskGroup;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/taskGroup")
public class GetTimedTaskGroupController {

    @Inject
    GetTaskGroup getTaskGroup;

    @Blocking
    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get Task Group", description = "Get all task groups")
    public Uni<Result> getAll() {
        return Streams.toApiResult(getTaskGroup.getAll());
    }

}
