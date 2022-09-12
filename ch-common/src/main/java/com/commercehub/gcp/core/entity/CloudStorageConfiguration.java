package com.commercehub.gcp.core.entity;

public class CloudStorageConfiguration {

    public static final String BUCKET_ETL = "etl";

    public final String bucketETL;

    public CloudStorageConfiguration(String bucketETL) {
        this.bucketETL = bucketETL;
    }

    @Override
    public String toString() {
        return "CloudStorageConfiguration{" +
                "bucketETL='" + bucketETL + '\'' +
                '}';
    }

}
