package com.commercehub.common;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final int POS_ZERO = 0;

    public static <T> T fromJson(Class<T> type, String json) throws IOException {
        Moshi moshi = new Moshi.Builder()
                .add(new BigDecimalAdapter())
                .add(new InstantAdapter())
                .build();

        return moshi.adapter(type).fromJson(json);
    }

    public static <T> List<T> fromJson(Class<T> type, List<String> jsonList) throws IOException {
        JsonAdapter<T> adapter = new Moshi.Builder()
                .add(new BigDecimalAdapter())
                .add(new InstantAdapter())
                .build()
                .adapter(type);

        List<T> result = new ArrayList<>();
        for ( String json : jsonList ) { result.add(adapter.fromJson(json)); }
        return result;
    }

    public static <T> String toJson(Class<T> type, T data) {
        Moshi moshi = new Moshi.Builder()
                .add(new BigDecimalAdapter())
                .add(new InstantAdapter())
                .build();

        return moshi.adapter(type).toJson(data);
    }

    public static <T> String toJson(Class<T> type, List<T> values) {
        JsonAdapter<T> adapter = new Moshi.Builder()
                .add(new BigDecimalAdapter())
                .add(new InstantAdapter())
                .build()
                .adapter(type);

        List<String> result = new ArrayList<>();
        for (T value : values) { result.add(adapter.toJson(value)); }
        return String.join("\n", result);
    }

    public static class BigDecimalAdapter {
        @FromJson
        BigDecimal fromJson(String data) {
            return new BigDecimal(data);
        }

        @ToJson
        String toJson(BigDecimal data) {
            return data.toString();
        }
    }

    public static class InstantAdapter {
        @FromJson
        Instant fromJson(long data) {
            return Instant.ofEpochMilli(data);
        }

        @ToJson
        String toJson(Instant data) {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
                    .withZone(ZoneId.of("UTC"));

            return formatter.format(data);
        }
    }

}
