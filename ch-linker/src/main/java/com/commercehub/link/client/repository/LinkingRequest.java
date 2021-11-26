package com.commercehub.link.client.repository;

import com.google.cloud.firestore.annotation.Exclude;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

@IgnoreExtraProperties
public class LinkingRequest {

    @Exclude
    private String id;

    private String partnerId;
    private String partnerSecret;
    private String redirectUri;

    public LinkingRequest() {}

    public LinkingRequest(LinkingRequest request) {
        this.id = request.id;
        this.partnerId = request.partnerId;
        this.partnerSecret = request.partnerSecret;
        this.redirectUri = request.redirectUri;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerSecret() {
        return partnerSecret;
    }

    public void setPartnerSecret(String partnerSecret) {
        this.partnerSecret = partnerSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
