package com.commercehub.gcp.core.usecase;

import java.util.Map;

public interface GetCloudTaskUrl {

    String execute(String baseUri, String path, Map<String,Object> params);

}
