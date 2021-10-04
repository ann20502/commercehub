package com.commercehub.link.controller;

import com.commercehub.link.client.LinkClient;
import com.commercehub.link.qualifier.LinkPreferred;
import org.jboss.resteasy.reactive.RestResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

@Path("/link")
public class AuthorizationRequestController {

    @Inject
    @LinkPreferred
    LinkClient linkClient;

    @GET
    @Path("/login/{client}")
    public RestResponse<Void> execute(@PathParam("client") String client) {
        URI uri = getUri();
        return RestResponse.temporaryRedirect(uri);
    }

    private URI getUri() {
        UriBuilder builder = UriBuilder.fromUri(linkClient.authorizationRedirect().redirectUri());
        Map<String,Object> params = linkClient.authorizationRedirect().param();
        for ( Map.Entry<String,Object> entry  : params.entrySet() ) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

}
