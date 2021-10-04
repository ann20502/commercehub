package com.commercehub.link.client;

import io.smallrye.mutiny.Uni;

public interface TokenResponseHandler {

    Uni<AuthorizationResponse> handle(Uni<TokenResponse> tokenResponse);

}
