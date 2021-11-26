package com.commercehub.etl.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.vm.SetupViewModel;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * TODO: Ensure 1 request at a time
 *
 */
@Path("/dataset/create")
public class CreateDatasetController {

    @Inject
    SetupViewModel viewModel;

    @Blocking
    @POST
    @Operation(summary = "Create dataset schema", description = "Create dataset for linking that has not been SETUP")
    public Uni<Result> execute() {
        return Streams.toApiResult(viewModel.createDatasetIfNotExist());
    }

}
