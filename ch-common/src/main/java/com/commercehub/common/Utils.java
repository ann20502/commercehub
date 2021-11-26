package com.commercehub.common;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
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

    private static class BigDecimalAdapter {
        @FromJson
        BigDecimal fromJson(String data) {
            return new BigDecimal(data);
        }

        @ToJson
        String toJson(BigDecimal data) {
            return data.toString();
        }
    }

    private static class InstantAdapter {
        @FromJson
        Instant fromJson(long data) {
            return Instant.ofEpochMilli(data);
        }

        @ToJson
        long toJson(Instant data) {
            return data.toEpochMilli();
        }
    }

}
