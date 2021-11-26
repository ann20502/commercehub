package com.commercehub.link.client.shopee;

import com.commercehub.link.client.AuthorizationCode;
import com.commercehub.link.client.TokenClient;
import com.commercehub.link.client.TokenResponse;
import com.commercehub.link.client.repository.LinkingRequest;
import com.commercehub.link.exception.LinkRuntimeException;
import com.commercehub.link.qualifier.LinkQualifier;
import com.commercehub.rest.shopee.ShopeeShopService;
import com.commercehub.rest.shopee.ShopeeTokenService;
import com.commercehub.rest.shopee.input.GetAccessTokenInput;
import com.commercehub.rest.shopee.input.PublicApiCommonParam;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
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
    @RestClient
    ShopeeTokenService tokenService;

    @Inject
    @RestClient
    ShopeeShopService shopService;

    @Override
    public Uni<TokenResponse> sendRequest(LinkingRequest request, AuthorizationCode authorizationCode) {
        AuthorizationCodeShopee code = (AuthorizationCodeShopee) authorizationCode;
        int mPartnerId = Integer.parseInt(request.getPartnerId());

        PublicApiCommonParam commonParam = new PublicApiCommonParam(mPartnerId, request.getPartnerSecret());
        int shopId = Integer.parseInt(code.shopId());

        GetAccessTokenInput input = new GetAccessTokenInput(
                code.authorizationCode(),
                mPartnerId,
                shopId
        );

        return tokenService
                .getAccessToken(commonParam, input)
                .map(output -> {
                    if ( output.getError() != null && !output.getError().isEmpty() ) {
                        throw new LinkRuntimeException("Unable to link with shopee: " + output.getError() + " - " + output.getMessage());
                    }
                    return output;
                })
                .flatMap(output -> {
                    ShopApiCommonParam shopApiCommonParam = new ShopApiCommonParam(
                            mPartnerId,
                            request.getPartnerSecret(),
                            output.getAccess_token(),
                            shopId
                    );

                    return shopService
                            .getShopInfo(shopApiCommonParam)
                            .map(shopOutput -> getTokenResponse(output, shopOutput, request.getPartnerId(), code.shopId()));
                });
    }

    private TokenResponse getTokenResponse(GetAccessTokenOutput accessTokenOutput, GetShopInfoOutput shopInfoOutput, String partnerId, String shopId) {
        return new TokenResponseShopeeBuilder()
                .setRequestId(accessTokenOutput.getRequest_id())
                .setPartnerId(partnerId)
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
