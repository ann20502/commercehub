package com.commercehub.linker.detail.repository;

import com.commercehub.linker.core.entity.Linking;
import com.commercehub.linker.core.entitybuilder.LinkingBuilder;

import java.time.Instant;

public class LinkingTransformer {

    public static Linking from(FSLinking linking) {
        Instant businessStartDate = linking.getBusinessStartDate() == null ? null : linking.getBusinessStartDate().toInstant();
        return new LinkingBuilder()
                .setId(linking.getId())
                .setStatus(linking.getStatus())
                .setPartnerId(linking.getPartnerId())
                .setPlatform(linking.getPlatform())
                .setShopId(linking.getShopId())
                .setShopName(linking.getShopName())
                .setShopStatus(linking.getShopStatus())
                .setShopRegion(linking.getShopRegion())
                .setHasLink(linking.isLink())
                .setHasSetup(linking.isSetup())
                .setBusinessStartDate(businessStartDate)
                .createLinking();
    }

}
