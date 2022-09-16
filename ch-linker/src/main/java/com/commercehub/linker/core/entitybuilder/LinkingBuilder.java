package com.commercehub.linker.core.entitybuilder;

import com.commercehub.linker.core.entity.Linking;

import java.time.Instant;

public class LinkingBuilder {
    private String id;
    private String status;
    private String partnerId;
    private String platform;
    private String shopId;
    private String shopName;
    private String shopStatus;
    private String shopRegion;
    private boolean hasLink;
    private boolean hasSetup;
    private Instant businessStartDate;

    public LinkingBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public LinkingBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public LinkingBuilder setPartnerId(String partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public LinkingBuilder setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public LinkingBuilder setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public LinkingBuilder setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public LinkingBuilder setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
        return this;
    }

    public LinkingBuilder setShopRegion(String shopRegion) {
        this.shopRegion = shopRegion;
        return this;
    }

    public LinkingBuilder setHasLink(boolean hasLink) {
        this.hasLink = hasLink;
        return this;
    }

    public LinkingBuilder setHasSetup(boolean hasSetup) {
        this.hasSetup = hasSetup;
        return this;
    }

    public LinkingBuilder setBusinessStartDate(Instant businessStartDate) {
        this.businessStartDate = businessStartDate;
        return this;
    }

    public Linking createLinking() {
        return new Linking(id, status, partnerId, platform, shopId, shopName, shopStatus, shopRegion, hasLink, hasSetup, businessStartDate);
    }
}