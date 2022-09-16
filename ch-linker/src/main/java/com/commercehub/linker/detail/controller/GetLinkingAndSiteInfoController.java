package com.commercehub.linker.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.linker.core.usecase.GetLinkingAndSiteInfo;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/linking")
public class GetLinkingAndSiteInfoController {

    @Inject
    GetLinkingAndSiteInfo getLinkingAndSiteInfo;

    @Blocking
    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get linking and site info", description = "Get all linking along with site info")
    public Uni<Result> execute() {
        final boolean LINKED_ONLY = true;
        return Streams.toApiResult(getLinkingAndSiteInfo.getAll(LINKED_ONLY));
    }

    @Blocking
    @Path("/{documentId}")
    @GET
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get specific linking and site info", description = "Get specific linking and site info")
    public Uni<Result> getById(@NotEmpty @PathParam("documentId") String documentId) {
        return Streams.toApiResult(getLinkingAndSiteInfo.getJustOne(documentId));
    }

}
