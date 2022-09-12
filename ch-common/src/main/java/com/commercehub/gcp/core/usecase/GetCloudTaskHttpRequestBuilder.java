package com.commercehub.gcp.core.usecase;

import com.google.cloud.tasks.v2.HttpRequest;

public interface GetCloudTaskHttpRequestBuilder {

    HttpRequest.Builder execute();

}
