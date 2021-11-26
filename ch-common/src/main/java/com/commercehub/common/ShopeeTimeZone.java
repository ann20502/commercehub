package com.commercehub.common;

import java.util.HashMap;
import java.util.Map;

public class ShopeeTimeZone {

    private static Map<String,String> TIMEZONE_BY_REGION = new HashMap<>() {{
        put("MY", "Asia/Kuala_Lumpur");
        put("SG", "Asia/Singapore");
        put("ID", "Asia/Jakarta");
    }};

    public static String getTimeZone(String shopRegion) {
        return TIMEZONE_BY_REGION.get(shopRegion);
    }

}
