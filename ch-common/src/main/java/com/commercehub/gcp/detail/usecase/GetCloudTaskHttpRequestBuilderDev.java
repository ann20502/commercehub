package com.commercehub.gcp.detail.usecase;

import com.commercehub.gcp.core.usecase.GetCloudTaskHttpRequestBuilder;
import com.google.cloud.tasks.v2.HttpRequest;

public class GetCloudTaskHttpRequestBuilderDev implements GetCloudTaskHttpRequestBuilder {

    @Override
    public HttpRequest.Builder execute() {
        return HttpRequest.newBuilder();
    }

}
