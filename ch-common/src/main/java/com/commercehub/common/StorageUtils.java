package com.commercehub.common;

public class StorageUtils {

    private static final String DELIM_SLASH = "/";
    private static final String PREFIX = "gs://";

    public static String getFullPath(String bucketName, String relativePath) {
        return PREFIX + bucketName + DELIM_SLASH + relativePath;
    }

}
