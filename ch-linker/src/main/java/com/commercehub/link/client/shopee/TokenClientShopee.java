package com.commercehub.link.client.shopee;

import com.commercehub.link.client.AuthorizationCode;
import com.commercehub.link.client.LinkClientConfiguration;
import com.commercehub.link.client.TokenClient;
import com.commercehub.link.client.TokenResponse;
import com.commercehub.link.exception.LinkRuntimeException;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifier;
import com.commercehub.rest.shopee.ShopeeShopService;
import com.commercehub.rest.shopee.ShopeeTokenService;
import com.commercehub.rest.shopee.input.GetAccessTokenInput;
import com.commercehub.rest.shopee.output.GetAccessTokenOutput;
import com.commercehub.rest.shopee.output.GetShopInfoOutput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@LinkQualifier("shopee")
public class TokenClientShopee implements TokenClient {

    @Inject
    @LinkPreferred
    LinkClientConfiguration configuration;

    @Inject
    @RestClient
    ShopeeTokenService tokenService;

    @Inject
    @RestClient
    ShopeeShopService shopService;

    @Override
    public Uni<TokenResponse> sendRequest(AuthorizationCode authorizationCode) {
        AuthorizationCodeShopee code = (AuthorizationCodeShopee) authorizationCode;
        GetAccessTokenInput input = new GetAccessTokenInput(
                code.authorizationCode(),
                Integer.parseInt(configuration.clientId()),
                Integer.parseInt(code.shopId())
        );

        return tokenService
                .getAccessToken(input)
                .map(output -> {
                    if ( output.getError() != null && !output.getError().isEmpty() ) {
                        throw new LinkRuntimeException("Unable to link with shopee: " + output.getError() + " - " + output.getMessage());
                    }
                    return output;
                })
                .flatMap(output -> shopService
                        .getShopInfo(output.getAccess_token(), Integer.parseInt(code.shopId()))
                        .map(shopOutput -> getTokenResponse(output, shopOutput, code.shopId()))
                );
    }

    private TokenResponse getTokenResponse(GetAccessTokenOutput accessTokenOutput, GetShopInfoOutput shopInfoOutput,  String shopId) {
        return new TokenResponseShopeeBuilder()
                .setRequestId(accessTokenOutput.getRequest_id())
                .setShopId(shopId)
                .setShopName(shopInfoOutput.getShop_name())
                .setShopStatus(shopInfoOutput.getStatus())
                .setShopRegion(shopInfoOutput.getRegion())
                .setAccessToken(accessTokenOutput.getAccess_token())
                .setAccessTokenExpireInSecond(accessTokenOutput.getExpire_in())
                .setRefreshToken(accessTokenOutput.getRefresh_token())
                .setMerchantIds(accessTokenOutput.getMerchant_id_list())
                .setShopIds(accessTokenOutput.getShop_id_list())
                .createTokenResponseShopee();
    }

}
