package com.commercehub.etl.detail.common;

public class BQUtils {

    public static String getTableName(String projectId, String dataset, String tableName) {
        return projectId + "." + dataset + "." + tableName;
    }

}
