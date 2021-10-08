package com.commercehub.common;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ShopeeUtils {

    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }

    public static String getBaseString(final String partnerId, final String apiPath, final long timestamp) {
        return partnerId + apiPath + timestamp;
    }

    public static String getBaseStringShopApi(final String partnerId, final String apiPath, final long timestamp, final String accessToken, final long shopId) {
        return partnerId + apiPath + timestamp + accessToken + shopId;
    }

    public static String getSignature(final String baseString, final String partnerKey) {
        try {
            final Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(
                    partnerKey.getBytes("UTF-8"), "HmacSHA256"
            );
            sha256_HMAC.init(secret_key);
            return new String(Hex.encodeHex(sha256_HMAC.doFinal(baseString.getBytes("UTF-8"))));
        } catch(NoSuchAlgorithmException ex) {
            System.out.println("HMAC SHA256 algorithm not found: " + ex.getMessage());
            return "";
        } catch (UnsupportedEncodingException uex) {
            System.out.println("Unsupported Encoding: " + uex.getMessage());
            return "";
        } catch (InvalidKeyException ike) {
            System.out.println("Invalid Key: " + ike.getMessage());
            return "";
        }
    }

    public static long getExpiry(long currentTimeMillis, Integer expireInSecond) {
        final long BUFFER_10_SECONDS = 10000L;
        final long expireInMillis = expireInSecond * 1000L;
        return currentTimeMillis + expireInMillis - BUFFER_10_SECONDS;
    }

}
