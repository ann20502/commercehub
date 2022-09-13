package com.commercehub.db.detail.repository;

import com.commercehub.db.core.entity.Linking;
import com.commercehub.db.core.entitybuilder.LinkingBuilder;

import java.time.Instant;

public class LinkingTransformer {

    public static Linking from(FSLinking linking) {
        Instant accessTokenExpiry = linking.getAccessTokenExpiry() == null ? null : linking.getAccessTokenExpiry().toInstant();
        Instant refreshTokenExpiry = linking.getRefreshTokenExpiry() == null ? null : linking.getRefreshTokenExpiry().toInstant();
        Instant businessStartDate = linking.getBusinessStartDate() == null ? null : linking.getBusinessStartDate().toInstant();

        return new LinkingBuilder()
                .setId(linking.getId())
                .setStatus(linking.getStatus())
                .setPlatform(linking.getPlatform())
                .setPartnerId(linking.getPartnerId())
                .setPartnerSecret(linking.getPartnerSecret())
                .setShopId(linking.getShopId())
                .setShopName(linking.getShopName())
                .setShopStatus(linking.getShopStatus())
                .setShopRegion(linking.getShopRegion())
                .setAccessToken(linking.getAccessToken())
                .setAccessTokenExpiry(accessTokenExpiry)
                .setRefreshToken(linking.getRefreshToken())
                .setRefreshTokenExpiry(refreshTokenExpiry)
                .setHasLink(linking.isLink())
                .setHasSetup(linking.isSetup())
                .setBusinessStartDate(businessStartDate)
                .createLinking();
    }

}
