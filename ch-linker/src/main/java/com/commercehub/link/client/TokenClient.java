package com.commercehub.link.client;

import io.smallrye.mutiny.Uni;

public interface TokenClient {

    Uni<TokenResponse> sendRequest(AuthorizationCode authorizationCode);

}
