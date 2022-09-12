package com.commercehub.etl.detail.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.etl.core.usecase.linking.AccessTokenRenewal;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/platform/token/renew")
public class RenewAccessTokenController {

    @Inject
    AccessTokenRenewal accessTokenRenewal;

    @Blocking
    @POST
    @Operation(summary = "Renew access token", description = "Renew access token for all ACTIVE linking that are expiring in 30 minutes")
    public Uni<Result> execute() {
        return Streams.toApiResult(accessTokenRenewal.renew());
    }

}
