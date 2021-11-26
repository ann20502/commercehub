package com.commercehub.link.client.shopee;

import com.commercehub.common.ShopeeUtils;

import java.util.HashMap;
import java.util.Map;

public class ShopeeLinkClientUtils {

    private static final String PARAM_PARTNER_ID = "partner_id";
    private static final String PARAM_REDIRECT = "redirect";
    private static final String PARAM_SIGN = "sign";
    private static final String PARAM_TIMESTAMP = "timestamp";

    public static Map<String,Object> getRedirectParam(String clientId, String clientSecret, String targetPath, String redirectUri) {
        final long TIMESTAMP = ShopeeUtils.getCurrentTimestamp();
        final String BASE_STRING = ShopeeUtils.getBaseString(clientId, targetPath, TIMESTAMP);
        final String SIGNATURE = ShopeeUtils.getSignature(BASE_STRING, clientSecret);

        Map<String,Object> result = new HashMap<>();
        result.put(PARAM_PARTNER_ID, clientId);
        result.put(PARAM_REDIRECT, redirectUri);
        result.put(PARAM_SIGN, SIGNATURE);
        result.put(PARAM_TIMESTAMP, Long.toString(ShopeeUtils.getCurrentTimestamp()));
        return result;
    }

}
