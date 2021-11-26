package com.commercehub.link.controller;

import com.commercehub.link.client.LinkClient;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.client.repository.LinkingRequest;
import com.commercehub.link.client.repository.LinkingRequestRepository;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/link")
public class AuthorizationCodeController {

    @Inject
    Logger log;

    @Inject
    UriInfo uriInfo;

    @Inject
    @LinkPreferred
    LinkClient linkClient;

    @Inject
    LinkingRequestRepository repository;

    @Inject
    HttpServerRequest request;

    @Inject
    HttpServerResponse response;

    @GET
    @Path("/callback/{client}/{state}")
    @Operation(summary = "Linking Authorization Callback", description = "Callback triggers by individual platform for authorization purpose")
    public Uni<RestResponse<Void>> execute(@PathParam("client") String client, @PathParam("state") String state) {
        LinkingRequest request = repository.get(state); // Bad design, should use a multi cast stream
        return linkClient.onCallback(request, uriInfo.getQueryParameters())
            .map(response -> request.getRedirectUri())
            .map(redirectUri -> redirectUri == null ? "/" : redirectUri)
            .map(redirectUri -> UriBuilder.fromUri(redirectUri).build())
            .map(RestResponse::seeOther)
            .onFailure().recoverWithItem(error -> {
                // In case of failure, simply redirect
                log.error("Authorization code callback error: " + error.getMessage());
                String redirectUri = request.getRedirectUri() == null ? "/" : request.getRedirectUri();
                URI result = UriBuilder.fromUri(redirectUri).build();
                return RestResponse.seeOther(result);
            });
    }

    private String getRedirectUri(String state) {
        Cookie cookie = request.getCookie(state);
        if ( cookie == null ) { return null; }
        String redirectUri = cookie.getValue();
        response.removeCookie(state, true);
        return redirectUri;
    }

}
