package com.commercehub.rs.utils;

import com.commercehub.rs.configuration.BigQueryConfiguration;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BQUtils {

    public static String getDatasetName(String platform, int shopId) {
        return platform + "_" + shopId;
    }

    public static String localDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(localDate);
    }

    public static LocalDate stringToLocalDate(String strLocalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(strLocalDate, formatter);
    }

    public static String getTableName(String projectId, String dataset, String tableName) {
        return projectId + "." + dataset + "." + tableName;
    }

}
