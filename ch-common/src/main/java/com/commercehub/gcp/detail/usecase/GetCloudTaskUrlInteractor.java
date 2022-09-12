package com.commercehub.gcp.detail.usecase;

import com.commercehub.gcp.core.usecase.GetCloudTaskUrl;

import javax.enterprise.context.Dependent;
import java.util.Map;

@Dependent
public class GetCloudTaskUrlInteractor implements GetCloudTaskUrl {

    @Override
    public String execute(String baseUri, String path, Map<String, Object> params) {
        String finalBaseUri = baseUri.endsWith("/") ? baseUri.substring(0, baseUri.length() - 1) : baseUri;
        String finalPath = path.startsWith("/") ? path.substring(1) : path;
        String queryString = getQueryString(params);
        return finalBaseUri + "/" + finalPath + "?" + queryString;
    }

    private String getQueryString(Map<String, Object> params) {
        StringBuilder result = new StringBuilder();
        int index = 0;
        for ( Map.Entry<String,Object> entry : params.entrySet() ) {
            if ( index++ != 0 ) { result.append("&"); }
            result.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return result.toString();
    }

}
