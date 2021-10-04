package com.commercehub.link.controller;

import com.commercehub.link.client.LinkClient;
import com.commercehub.link.qualifier.LinkPreferred;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.jboss.resteasy.reactive.RestResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/link")
public class AuthorizationCodeController {

    @Inject
    UriInfo uriInfo;

    @Inject
    @LinkPreferred
    LinkClient linkClient;

    @Inject
    HttpServerRequest request;

    @Inject
    HttpServerResponse response;

    /*
        TODO:
        1. Get login function ready - Home Page + Logged-in page !!!
        2. Get linking page ready
        3. Redirect to linking page
        4. Commit to git locally, quite a few things already
     */
    @GET
    @Path("/callback/{client}/{state}")
    public Uni<RestResponse<Void>> execute(@PathParam("client") String client, @PathParam("state") String state) {
        final String destination = getRedirectUri(state); // Not sure if this is the right way to do ...
        return linkClient.onCallback(uriInfo.getQueryParameters())
            .map(response -> destination)
            .map(redirectUri -> redirectUri == null ? "/" : redirectUri)
            .map(redirectUri -> UriBuilder.fromUri(redirectUri).build())
            .map(RestResponse::seeOther);
    }

    private String getRedirectUri(String state) {
        Cookie cookie = request.getCookie(state);
        if ( cookie == null ) { return null; }
        String redirectUri = cookie.getValue();
        response.removeCookie(state, true);
        return redirectUri;
    }

}
