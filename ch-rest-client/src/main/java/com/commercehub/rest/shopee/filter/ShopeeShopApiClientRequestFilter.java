package com.commercehub.rest.shopee.filter;

import com.commercehub.common.ShopeeUtils;
import com.commercehub.common.Utils;
import com.commercehub.rest.shopee.ShopeeConfiguration;
import io.netty.handler.codec.http.QueryStringDecoder;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
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
    private final String PARAM_TIMESTAMP = "timestamp";
    private final String PARAM_ACCESS_TOKEN = "access_token";
    private final String PARAM_SHOP_ID = "shop_id";
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
        Map<String,List<String>> params = new QueryStringDecoder(uri).parameters();
        String apiPath = uri.getPath();

        UriBuilder builder = UriBuilder.fromUri(uri);
        Map<String,Object> additionalParams = getAdditionalParams(params, apiPath);
        for ( Map.Entry<String,Object> entry : additionalParams.entrySet() ) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        return builder.build();
    }

    private Map<String,Object> getAdditionalParams(Map<String,List<String>> params, String apiPath) {
        String partnerId = configuration.clientId();
        long timestamp = ShopeeUtils.getCurrentTimestamp();
        String accessToken = getAccessToken(params);
        int shopId = getShopId(params);

        String baseString = ShopeeUtils.getBaseStringShopApi(partnerId, apiPath, timestamp, accessToken, shopId);
        String sign = ShopeeUtils.getSignature(baseString, configuration.clientSecret());

        Map<String,Object> result = new HashMap<>();
        result.put(PARAM_PARTNER_ID, configuration.clientId());
        result.put(PARAM_TIMESTAMP, timestamp);
        result.put(PARAM_SIGN, sign);
        return  result;
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
