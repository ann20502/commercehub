package com.commercehub.link.client;

import com.commercehub.link.client.repository.LinkingRequest;
import io.smallrye.mutiny.Uni;

public interface TokenClient {

    Uni<TokenResponse> sendRequest(LinkingRequest request, AuthorizationCode authorizationCode);

}
