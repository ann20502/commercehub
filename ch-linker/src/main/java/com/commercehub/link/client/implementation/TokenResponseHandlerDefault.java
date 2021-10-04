package com.commercehub.link.client.implementation;

import com.commercehub.link.client.AuthorizationResponse;
import com.commercehub.link.client.TokenResponse;
import com.commercehub.link.client.TokenResponseHandler;
import com.commercehub.link.qualifier.LinkDefault;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;

@Dependent
@LinkDefault
public class TokenResponseHandlerDefault implements TokenResponseHandler {

    @Override
    public Uni<AuthorizationResponse> handle(Uni<TokenResponse> tokenResponse) {
        return tokenResponse
                .map(input -> new AuthorizationResponseDefault(
                        LinkClientDefault.NAME,
                        input.accessToken(),
                        input.accessTokenExpiry(),
                        input.refreshToken(),
                        input.refreshTokenExpiry()
                ));
    }

}
