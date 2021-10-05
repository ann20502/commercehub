package com.commercehub.link.filter;

import com.commercehub.link.client.LinkClientConfigurations;
import com.commercehub.link.request.LinkRequestDataHolder;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.container.ContainerRequestContext;

@ApplicationScoped
public class LinkFilter {

    @Inject
    LinkRequestDataHolder dataHolder;

    @Inject
    LinkClientConfigurations configurations;

    @ServerRequestFilter
    public void setDataHolder(ContainerRequestContext context) {
        if ( isLinkRequest(context) ) {
            final String client = getClient(context);
            if ( isInputValid(client) && isConfigured(client) ) {
                dataHolder.setClient(client);
            } else {
                System.out.println("Bad link request, client [" + client + "], throw it away !");
                throw new BadRequestException();
            }
        }
    }

    private boolean isLinkRequest(ContainerRequestContext context) {
        final String PATH_AUTHORIZATION_REQUEST = "/link/login";
        final String PATH_AUTHORIZATION_CODE = "/link/callback";
        final String PATH_CANCEL_AUTHORIZATION_REQUEST = "/unlink/login";
        final String PATH_CANCEL_AUTHORIZATION_CALLBACK = "/unlink/callback";
        return context.getUriInfo().getPath().startsWith(PATH_AUTHORIZATION_REQUEST)
                || context.getUriInfo().getPath().startsWith(PATH_AUTHORIZATION_CODE)
                || context.getUriInfo().getPath().startsWith(PATH_CANCEL_AUTHORIZATION_REQUEST)
                || context.getUriInfo().getPath().startsWith(PATH_CANCEL_AUTHORIZATION_CALLBACK);
    }

    private String getClient(ContainerRequestContext context) {
        return context.getUriInfo().getPathParameters().getFirst("client");
    }

    private boolean isInputValid(String client) {
        return client != null && !client.isEmpty();
    }

    private boolean isConfigured(String client) {
        return configurations.clients().get(client) != null;
    }

}
