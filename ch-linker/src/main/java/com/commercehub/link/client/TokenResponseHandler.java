package com.commercehub.link.client;

import com.commercehub.link.client.repository.LinkingRequest;
import io.smallrye.mutiny.Uni;

public interface TokenResponseHandler {

    Uni<AuthorizationResponse> handle(LinkingRequest request, Uni<TokenResponse> tokenResponse);

}
