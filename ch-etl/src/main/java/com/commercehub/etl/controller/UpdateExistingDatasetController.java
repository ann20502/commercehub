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
 * TODO: Bear this portion in CI / CD process
 * For example, create a standalone java app to be used by CI / CD process
 * Most likely, it has to take place before app compilation & deployment
 *
 */
@Path("/dataset/update")
public class UpdateExistingDatasetController {

    @Inject
    SetupViewModel viewModel;

    @Blocking
    @POST
    @Operation(summary = "Update dataset schema", description = "Update dataset schema for all ACTIVE linking")
    public Uni<Result> execute() {
        return Streams.toApiResult(viewModel.updateExistingDataset());
    }

}
