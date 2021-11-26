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

/**
 *
 * This library is implemented with the concept of 1 partner work with multiple shops.
 * As such, access token & shop id will be supplied by library user,
 * and Partner particulars (id & secret) will be configured in this project.
 *
 */
public class ShopeeShopApiClientRequestFilter implements ClientRequestFilter {

    private final String PARAM_PARTNER_ID = "partner_id";
    private final String PARAM_PARTNER_SECRET = "partner_secret";
    private final String PARAM_TIMESTAMP = "timestamp";
    private final String PARAM_ACCESS_TOKEN = "access_token";
    private final String PARAM_SHOP_ID = "shop_id";
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

    private Map<String,Object> getAdditionalParams(Map<String,List<String>> params, String apiPath) {
        String partnerId = getPartnerId(params);
        String partnerSecret = getPartnerSecret(params);

        long timestamp = ShopeeUtils.getCurrentTimestamp();
        String accessToken = getAccessToken(params);
        int shopId = getShopId(params);

        String baseString = ShopeeUtils.getBaseStringShopApi(partnerId, apiPath, timestamp, accessToken, shopId);
        String sign = ShopeeUtils.getSignature(baseString, partnerSecret);

        Map<String,Object> result = new HashMap<>();
        result.put(PARAM_PARTNER_ID, partnerId);
        result.put(PARAM_TIMESTAMP, timestamp);
        result.put(PARAM_SIGN, sign);
        return  result;
    }

    private Map<String,List<String>> getFinalParams(Map<String,List<String>> params, String apiPath) {
        String id = getPartnerId(params);
        String secret = getPartnerSecret(params);

        long timestamp = ShopeeUtils.getCurrentTimestamp();
        String accessToken = getAccessToken(params);
        int shopId = getShopId(params);

        String baseString = ShopeeUtils.getBaseStringShopApi(id, apiPath, timestamp, accessToken, shopId);
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

    private String getAccessToken(Map<String, List<String>> params) {
        List<String> accessTokens = params.get(PARAM_ACCESS_TOKEN);
        return accessTokens.isEmpty() ? "" : accessTokens.get(Utils.POS_ZERO);
    }

    private int getShopId(Map<String, List<String>> params) {
        List<String> shopIds = params.get(PARAM_SHOP_ID);
        return shopIds.isEmpty() ? 0 : Integer.parseInt(shopIds.get(Utils.POS_ZERO));
    }

}
