package com.commercehub.rs.detail.common;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class BQUtils {

    public static String getDatasetName(String platform, int shopId) {
        return platform + "_" + shopId;
    }

    public static String getDatasetName(String platform, String shopId) {
        return platform +  "_" + shopId;
    }

    public static String localDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(localDate);
    }

    public static LocalDate stringToLocalDate(String strLocalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(strLocalDate, formatter);
    }

    public static YearMonth stringToYearMonth(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return YearMonth.parse(date, formatter);
    }

    public static String getTableName(String projectId, String dataset, String tableName) {
        return projectId + "." + dataset + "." + tableName;
    }

}
