package com.commercehub.rest.shopee.filter;

import com.commercehub.common.ShopeeUtils;
import com.commercehub.common.Utils;
import io.netty.handler.codec.http.QueryStringDecoder;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopeePublicApiClientRequestFilter implements ClientRequestFilter {

    private final String PARAM_PARTNER_ID = "partner_id";
    private final String PARAM_PARTNER_SECRET = "partner_secret";
    private final String PARAM_TIMESTAMP = "timestamp";
    private final String PARAM_SIGN = "sign";

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        URI uri = getUriWithCommonParam(requestContext);
//        System.out.println("New URI: " + uri.toString());
        requestContext.setUri(uri);
    }

    private URI getUriWithCommonParam(ClientRequestContext requestContext) {
        URI uri = requestContext.getUri();
        Map<String,List<String>> params = new QueryStringDecoder(uri).parameters();

        UriBuilder builder = UriBuilder.fromUri(uri).replaceQuery(null);
        String apiPath = uri.getRawPath();

        Map<String,List<String>> finalParams = getFinalParams(params, apiPath);
        for ( Map.Entry<String,List<String>> entry : finalParams.entrySet() ) {
            String value = String.join(",", entry.getValue());
            builder.queryParam(entry.getKey(), value);
        }
        return builder.build();
    }

    private Map<String,Object> getPublicApiParam(Map<String,List<String>> params, String apiPath) {
        String partnerId = getPartnerId(params);
        String partnerSecret = getPartnerSecret(params);

        long timestamp = ShopeeUtils.getCurrentTimestamp();
        String baseString = ShopeeUtils.getBaseString(partnerId, apiPath, timestamp);
        String sign = ShopeeUtils.getSignature(baseString, partnerSecret);

        Map<String,Object> result = new HashMap<>();
        result.put(PARAM_PARTNER_ID, Long.parseLong(partnerId));
        result.put(PARAM_TIMESTAMP, timestamp);
        result.put(PARAM_SIGN, sign);
        return result;
    }

    private Map<String,List<String>> getFinalParams(Map<String,List<String>> params, String apiPath) {
        String id = getPartnerId(params);
        String secret = getPartnerSecret(params);

        long timestamp = ShopeeUtils.getCurrentTimestamp();
        String baseString = ShopeeUtils.getBaseString(id, apiPath, timestamp);
        String sign = ShopeeUtils.getSignature(baseString, secret);

        Map<String,List<String>> result = new HashMap<>(params);
        result.remove(PARAM_PARTNER_SECRET);
        result.put(PARAM_TIMESTAMP, Arrays.asList(Long.toString(timestamp)));
        result.put(PARAM_SIGN, Arrays.asList(sign));
        return result;
    }

    private String getPartnerId(Map<String, List<String>> params) {
        List<String> partnerIds = params.get(PARAM_PARTNER_ID);
        return partnerIds.isEmpty() ? "" : partnerIds.get(Utils.POS_ZERO);
    }

    private String getPartnerSecret(Map<String,List<String>> params) {
        List<String> partnerSecrets = params.get(PARAM_PARTNER_SECRET);
        return partnerSecrets.isEmpty() ? "" : partnerSecrets.get(Utils.POS_ZERO);
    }

}
