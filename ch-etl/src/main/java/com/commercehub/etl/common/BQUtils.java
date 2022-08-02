package com.commercehub.etl.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BQUtils {

    public static String getTableName(String projectId, String dataset, String tableName) {
        return projectId + "." + dataset + "." + tableName;
    }

}
