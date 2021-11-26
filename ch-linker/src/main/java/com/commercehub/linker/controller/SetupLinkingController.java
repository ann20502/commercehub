package com.commercehub.linker.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.vm.LinkingViewModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("/linking/setup")
public class SetupLinkingController {

    @Inject
    LinkingViewModel viewModel;

    @POST
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Setup Linking", description = "Fill up linking information")
    public Uni<Result> execute(@Valid Input input) {
        return Streams.toApiResult(
                viewModel.setupLinking(
                        input.documentId,
                        input.getFieldToUpdate()
                )
        );
    }

    public static class Input {

        @NotEmpty
        String documentId;

        @NotNull
        @JsonFormat(pattern = "dd-MMM-yyyy")
        private Date businessStartDate;

        public Input() {}

        public Input(String documentId, Date businessStartDate) {
            this.documentId = documentId;
            this.businessStartDate = businessStartDate;
        }

        public String getDocumentId() {
            return documentId;
        }

        public Date getBusinessStartDate() {
            return businessStartDate;
        }

        @JsonIgnore
        public Map<String,Object> getFieldToUpdate() {
            Map<String,Object> result = new HashMap<>();
            result.put("businessStartDate", businessStartDate);
            return result;
        }

        @Override
        public String toString() {
            return "Input{" +
                    "documentId='" + documentId + '\'' +
                    ", businessStartDate=" + businessStartDate +
                    '}';
        }
    }

}
