package com.commercehub.etl.domain.service;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.qualifier.Platform;
import com.commercehub.rest.shopee.ShopeeTokenService;
import com.commercehub.rest.shopee.input.PublicApiCommonParam;
import com.commercehub.rest.shopee.input.RefreshAccessTokenInput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Platform("shopee")
@Dependent
public class RenewTokenServiceShopee implements RenewTokenService {

    @Inject
    @RestClient
    ShopeeTokenService shopeeTokenService;

    @Override
    public Uni<RenewTokenResult> renew(Linking linking) {
        int partnerId = Integer.parseInt(linking.getPartnerId());
        PublicApiCommonParam commonParam = new PublicApiCommonParam(
                partnerId,
                linking.getPartnerSecret()
        );

        RefreshAccessTokenInput input = new RefreshAccessTokenInput(
                linking.getRefreshToken(),
                partnerId,
                Integer.parseInt(linking.getShopId())
        );

        return shopeeTokenService.refreshAccessToken(commonParam, input)
                .map(output -> new RenewTokenResultShopee(
                        output.getAccess_token(),
                        output.getExpire_in(),
                        output.getRefresh_token(),
                        0
                ));
    }

}
