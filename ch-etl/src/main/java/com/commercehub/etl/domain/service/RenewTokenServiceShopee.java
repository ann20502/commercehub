package com.commercehub.etl.domain.service;

import com.commercehub.etl.domain.entity.Linking;
import com.commercehub.etl.domain.entity.RenewTokenResult;
import com.commercehub.etl.qualifier.Platform;
import com.commercehub.rest.shopee.ShopeeConfiguration;
import com.commercehub.rest.shopee.ShopeeTokenService;
import com.commercehub.rest.shopee.input.RefreshAccessTokenInput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Platform("shopee")
@Dependent
public class RenewTokenServiceShopee implements RenewTokenService {

    @Inject
    ShopeeConfiguration shopeeConfiguration;

    @Inject
    @RestClient
    ShopeeTokenService shopeeTokenService;

    @Override
    public Uni<RenewTokenResult> renew(Linking linking) {
        RefreshAccessTokenInput input = new RefreshAccessTokenInput(
                linking.getRefreshToken(),
                Integer.parseInt(shopeeConfiguration.clientId()),
                Integer.parseInt(linking.getShopId())
        );
        return shopeeTokenService.refreshAccessToken(input)
                .map(output -> new RenewTokenResultShopee(
                        output.getAccess_token(),
                        output.getExpire_in(),
                        output.getRefresh_token(),
                        0
                ));
    }

    public static class RenewTokenResultShopee implements RenewTokenResult {

        public final String accessToken;
        public final int accessTokenExpireInSeconds;
        public final String refreshToken;
        public final int refreshTokenExpireInSeconds;

        public RenewTokenResultShopee(String accessToken, int accessTokenExpireInSeconds, String refreshToken, int refreshTokenExpireInSeconds) {
            this.accessToken = accessToken;
            this.accessTokenExpireInSeconds = accessTokenExpireInSeconds;
            this.refreshToken = refreshToken;
            this.refreshTokenExpireInSeconds = refreshTokenExpireInSeconds;
        }

        @Override
        public String accessToken() {
            return accessToken;
        }

        @Override
        public int accessTokenExpireInSeconds() {
            return accessTokenExpireInSeconds;
        }

        @Override
        public String refreshToken() {
            return refreshToken;
        }

        @Override
        public int refreshTokenExpireInSeconds() {
            return refreshTokenExpireInSeconds;
        }

    }

}
