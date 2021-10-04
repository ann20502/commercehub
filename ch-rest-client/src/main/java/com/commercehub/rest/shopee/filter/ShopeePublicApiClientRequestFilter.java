package com.commercehub.rest.shopee.filter;

import com.commercehub.common.ShopeeUtils;
import com.commercehub.rest.shopee.ShopeeConfiguration;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ShopeePublicApiClientRequestFilter implements ClientRequestFilter {

    private final String PARAM_PARTNER_ID = "partner_id";
    private final String PARAM_TIMESTAMP = "timestamp";
    private final String PARAM_SIGN = "sign";

    @Inject
    @Default
    ShopeeConfiguration configuration;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        URI uri = getUriWithCommonParam(requestContext);
        System.out.println("New URI: " + uri.toString());
        requestContext.setUri(uri);
    }

    private URI getUriWithCommonParam(ClientRequestContext requestContext) {
        URI uri = requestContext.getUri();
        UriBuilder builder = UriBuilder.fromUri(uri);
        String apiPath = uri.getRawPath();
        Map<String,Object> params = getPublicApiParam(apiPath);
        for ( Map.Entry<String,Object> entry : params.entrySet() ) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    private Map<String,Object> getPublicApiParam(String apiPath) {
        String partnerId = configuration.clientId();
        long timestamp = ShopeeUtils.getCurrentTimestamp();
        String baseString = ShopeeUtils.getBaseString(partnerId, apiPath, timestamp);
        String sign = ShopeeUtils.getSignature(baseString, configuration.clientSecret());

        Map<String,Object> result = new HashMap<>();
        result.put(PARAM_PARTNER_ID, Long.parseLong(partnerId));
        result.put(PARAM_TIMESTAMP, timestamp);
        result.put(PARAM_SIGN, sign);
        return result;
    }

}
