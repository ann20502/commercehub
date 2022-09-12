package com.commercehub.etl.detail.repository.linking;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entitybuilder.linking.LinkingBuilder;

import java.time.Instant;

public class LinkingTransformer {

    public static Linking transform(FSLinking fsLinking) {
        Instant accessTokenExpiry = fsLinking.getAccessTokenExpiry() == null ? null : fsLinking.getAccessTokenExpiry().toInstant();
        Instant refreshTokenExpiry = fsLinking.getRefreshTokenExpiry() == null ? null : fsLinking.getRefreshTokenExpiry().toInstant();
        Instant businessStartDate = fsLinking.getBusinessStartDate() == null ? null : fsLinking.getBusinessStartDate().toInstant();

        return new LinkingBuilder()
                .setId(fsLinking.getId())
                .setStatus(fsLinking.getStatus())
                .setPlatform(fsLinking.getPlatform())
                .setPartnerId(fsLinking.getPartnerId())
                .setPartnerSecret(fsLinking.getPartnerSecret())
                .setShopId(fsLinking.getShopId())
                .setShopName(fsLinking.getShopName())
                .setShopStatus(fsLinking.getShopStatus())
                .setShopRegion(fsLinking.getShopRegion())
                .setAccessToken(fsLinking.getAccessToken())
                .setAccessTokenExpiry(accessTokenExpiry)
                .setRefreshToken(fsLinking.getRefreshToken())
                .setRefreshTokenExpiry(refreshTokenExpiry)
                .setHasLink(fsLinking.isLink())
                .setHasSetup(fsLinking.isSetup())
                .setBusinessStartDate(businessStartDate)
                .createLinking();
    }

}
