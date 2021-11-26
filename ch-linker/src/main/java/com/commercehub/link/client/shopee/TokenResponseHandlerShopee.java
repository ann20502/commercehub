package com.commercehub.link.client.shopee;

import com.commercehub.common.TimeZoneUtils;
import com.commercehub.link.client.AuthorizationResponse;
import com.commercehub.link.client.TokenResponse;
import com.commercehub.link.client.TokenResponseHandler;
import com.commercehub.link.client.implementation.AuthorizationResponseDefault;
import com.commercehub.link.client.repository.Linking;
import com.commercehub.link.client.repository.LinkingBuilder;
import com.commercehub.link.client.repository.LinkingRepository;
import com.commercehub.link.client.repository.LinkingRequest;
import com.commercehub.link.exception.LinkRuntimeException;
import com.commercehub.link.qualifier.LinkQualifier;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@LinkQualifier("shopee")
public class TokenResponseHandlerShopee implements TokenResponseHandler {

    @Inject
    LinkingRepository repository;

    @Override
    public Uni<AuthorizationResponse> handle(LinkingRequest request, Uni<TokenResponse> tokenResponse) {
        return tokenResponse
                .map(response -> (TokenResponseShopee) response)
                .map(response -> {
                    Linking linking = getLinking(request, response);
                    boolean result = repository.insertOrUpdateIfExist(linking);
                    if ( !result ) { throw new LinkRuntimeException("Failed to insert/update linking"); }
                    return response;
                })
                .map(this::getResponse);
    }

    private Linking getLinking(LinkingRequest request, TokenResponseShopee token) {
        return new LinkingBuilder()
                .setStatus(Linking.STATUS_ACTIVE)
                .setPlatform(LinkClientShopee.NAME)
                .setPartnerId(request.getPartnerId())
                .setPartnerSecret(request.getPartnerSecret())
                .setShopId(token.getShopId())
                .setShopName(token.getShopName())
                .setShopStatus(token.getShopStatus())
                .setShopRegion(token.getShopRegion())
                .setAccessToken(token.accessToken())
                .setAccessTokenExpiry(TimeZoneUtils.getDate(token.accessTokenExpiry()))
                .setRefreshToken(token.refreshToken())
                .setLink(true)
                .setSetup(false)
                .setBusinessStartDate(null)
                .createLinking();
    }

    private AuthorizationResponse getResponse(TokenResponseShopee token) {
        return new AuthorizationResponseDefault(
                LinkClientShopee.NAME,
                token.accessToken(),
                token.accessTokenExpiry(),
                token.refreshToken(),
                token.refreshTokenExpiry()
        );
    }

}
