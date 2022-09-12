package com.commercehub.etl.detail.usecaseinteractor.linking;

import com.commercehub.common.ShopeeUtils;
import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.repository.LinkingRepository;
import com.commercehub.etl.core.usecase.linking.AccessTokenRenewalWorker;
import com.commercehub.rest.shopee.ShopeeTokenService;
import com.commercehub.rest.shopee.input.PublicApiCommonParam;
import com.commercehub.rest.shopee.input.RefreshAccessTokenInput;
import com.commercehub.rest.shopee.output.RefreshAccessTokenOutput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.Instant;

@Dependent
public class AccessTokenRenewalWorkerShopee implements AccessTokenRenewalWorker {

    @Inject
    @RestClient
    ShopeeTokenService shopeeTokenService;

    @Inject
    LinkingRepository repository;

    @Override
    public Uni<Boolean> renew(Linking linking) {
        return Uni.createFrom().item(linking)
                .flatMap(this::renewAccessToken)
                .map(output -> saveInDatabase(linking, output));
    }

    private Uni<RefreshAccessTokenOutput> renewAccessToken(Linking linking) {
        int partnerId = Integer.parseInt(linking.partnerId);
        PublicApiCommonParam commonParam = new PublicApiCommonParam(
                partnerId,
                linking.partnerSecret
        );

        RefreshAccessTokenInput input = new RefreshAccessTokenInput(
                linking.refreshToken,
                partnerId,
                Integer.parseInt(linking.shopId)
        );

        return shopeeTokenService.refreshAccessToken(commonParam, input);
    }

    private boolean saveInDatabase(Linking linking, RefreshAccessTokenOutput output) {
        long currentTimeMillis = System.currentTimeMillis();
        long accessTokenExpiry = ShopeeUtils.getExpiry(currentTimeMillis, output.getExpire_in());
        return repository.updateToken(
                linking.id,
                output.getAccess_token(),
                Instant.ofEpochMilli(accessTokenExpiry),
                output.getRefresh_token()
        );
    }

}
