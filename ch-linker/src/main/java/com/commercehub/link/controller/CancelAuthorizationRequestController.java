package com.commercehub.link.controller;

import com.commercehub.link.client.LinkClient;
import com.commercehub.link.qualifier.LinkPreferred;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

@Path("/unlink")
public class CancelAuthorizationRequestController {

    @Inject
    @LinkPreferred
    LinkClient linkClient;

    @GET
    @Path("/login/{client}")
    public Uni<CancelAuthorizationRedirectUri> execute(@PathParam("client") String client) {
        URI uri = getUri();
        CancelAuthorizationRedirectUri result = new CancelAuthorizationRedirectUri(uri.toString());
        return Uni.createFrom().item(result);
    }

    private URI getUri() {
        UriBuilder builder = UriBuilder.fromUri(linkClient.unlinkAuthorizationRedirect().redirectUri());
        Map<String,Object> params = linkClient.unlinkAuthorizationRedirect().param();
        for ( Map.Entry<String,Object> entry  : params.entrySet() ) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    public static class CancelAuthorizationRedirectUri {

        public final String uri;

        public CancelAuthorizationRedirectUri(String uri) {
            this.uri = uri;
        }
    }

}
