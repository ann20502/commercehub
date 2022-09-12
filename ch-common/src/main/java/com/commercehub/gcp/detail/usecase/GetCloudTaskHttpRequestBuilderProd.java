package com.commercehub.gcp.detail.usecase;

import com.commercehub.gcp.core.usecase.GetCloudTaskHttpRequestBuilder;
import com.google.cloud.tasks.v2.HttpRequest;
import com.google.cloud.tasks.v2.OidcToken;

public class GetCloudTaskHttpRequestBuilderProd implements GetCloudTaskHttpRequestBuilder {

    private final String serviceAccountEmail;

    public GetCloudTaskHttpRequestBuilderProd(String serviceAccountEmail) {
        this.serviceAccountEmail = serviceAccountEmail;
    }

    @Override
    public HttpRequest.Builder execute() {
        final OidcToken oidcToken = OidcToken.newBuilder()
                .setServiceAccountEmail(serviceAccountEmail)
                .build();
        return HttpRequest.newBuilder().setOidcToken(oidcToken);
    }

}
