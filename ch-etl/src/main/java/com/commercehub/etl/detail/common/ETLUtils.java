package com.commercehub.etl.detail.common;

import java.util.ArrayList;
import java.util.List;

public class ETLUtils {

    public static final String TABLE_ORDER_CREATE_TIME = "order_create_time";
    public static final String TABLE_SHOP_PERFORMANCE = "shop_performance";
    public static final String TABLE_ITEM = "item";

    public static List<Integer> getIndex(int pageSize, int noOfOccurrence) {
        List<Integer> result = new ArrayList<>();
        int cursor = 0;
        for (int i = 0; i < noOfOccurrence; i++) {
            result.add(cursor);
            cursor += pageSize;
        }
        return result;
    }

}
