package com.commercehub.link.controller;

import com.commercehub.link.client.AuthorizationRedirect;
import com.commercehub.link.client.LinkClient;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.client.repository.LinkingRequest;
import com.commercehub.link.client.repository.LinkingRequestRepository;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

@Path("/link")
public class AuthorizationRequestController {

    @Inject
    @LinkPreferred
    LinkClient linkClient;

    @Inject
    LinkingRequestRepository repository;

    @POST
    @Path("/login/{client}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Linking Request", description = "Generate linking request and return request uri")
    public Uni<AuthorizationRedirectUri> execute(@PathParam("client") String client, Partner partner) {
        LinkingRequest request = getLinkingRequest(partner);
        request = repository.add(request);

        URI uri = getUri(request);
        AuthorizationRedirectUri result = new AuthorizationRedirectUri(uri.toString());
        return Uni.createFrom().item(result);
    }

    private LinkingRequest getLinkingRequest(Partner partner) {
        LinkingRequest result = new LinkingRequest();
        result.setPartnerId(partner.getPartnerId());
        result.setPartnerSecret(partner.getPartnerSecret());
        result.setRedirectUri(partner.getRedirectUri());
        return result;
    }

    private URI getUri(LinkingRequest request) {
        AuthorizationRedirect authorizationRedirect = linkClient.authorizationRedirect();
        UriBuilder builder = UriBuilder.fromUri(authorizationRedirect.redirectUri());
        Map<String,Object> params = authorizationRedirect.param(request);
        for ( Map.Entry<String,Object> entry : params.entrySet() ) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    public static class Partner {

        private String partnerId;
        private String partnerSecret;
        private String redirectUri;

        public String getPartnerId() {
            return partnerId;
        }

        public String getPartnerSecret() {
            return partnerSecret;
        }

        public String getRedirectUri() {
            return redirectUri;
        }
    }

    public static class AuthorizationRedirectUri {

        public final String uri;

        public AuthorizationRedirectUri(String uri) {
            this.uri = uri;
        }
    }

}
