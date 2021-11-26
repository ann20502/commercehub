package com.commercehub.etl.common;

public class ETLUtils {

    public static final String BUCKET = "commercehub-etl";
    public static final String TABLE_ORDER_CREATE_TIME = "order_create_time";

    public static String getDatasetName(String platform, String shopId) {
        return platform + "_" + shopId;
    }

}
