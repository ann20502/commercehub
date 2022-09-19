package com.commercehub.etl.detail.repository.shop;

import com.commercehub.common.DatabaseUtils;
import com.commercehub.common.StorageUtils;
import com.commercehub.common.Utils;
import com.commercehub.etl.core.entity.shop.PerformanceShopee;
import com.commercehub.etl.core.repository.ShopRepositoryShopee;
import com.commercehub.etl.detail.common.ETLUtils;
import com.commercehub.etl.detail.repository.BlobIdWrapper;
import com.commercehub.etl.detail.repository.shop.transformer.BQPerformanceShopeeTransformer;
import com.commercehub.gcp.core.entity.CloudStorageConfiguration;
import com.commercehub.gcp.core.usecase.GetCloudStorageConfiguration;
import com.google.cloud.bigquery.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

@Dependent
public class BQShopRepositoryShopee implements ShopRepositoryShopee {

    @Inject
    Logger log;

    @Inject
    BigQuery bigquery;

    @Inject
    Storage storage;

    @Inject
    GetCloudStorageConfiguration getCloudStorageConfiguration;

    @Override
    public Uni<Boolean> savePerformance(String platform, String shopId, PerformanceShopee performance) {
        return Multi.createFrom().item(performance)
                .map(BQPerformanceShopeeTransformer::from)
                .map(this::saveInCloudStorage)
                .filter(blobIdWrapper -> saveInBigQuery(platform, shopId, blobIdWrapper))
                .filter(blobIdWrapper -> storage.delete(blobIdWrapper.blobId)) // Can be done by separate job
                .toUni()
                .map(blobIdWrapper -> Boolean.TRUE);
    }

    private BlobIdWrapper saveInCloudStorage(BQPerformanceShopee performance) {
        final String json = Utils.toJson(BQPerformanceShopee.class, performance);
        CloudStorageConfiguration configuration = getCloudStorageConfiguration.execute();
        BlobId blobId = BlobId.of(configuration.bucketETL, getObjectName());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, json.getBytes(StandardCharsets.UTF_8), Storage.BlobTargetOption.doesNotExist());
        return new BlobIdWrapper(blobId, StorageUtils.getFullPath(blobId.getBucket(), blobId.getName()));
    }

    private String getObjectName() {
        return "shop-performance-" + System.currentTimeMillis();
    }

    private boolean saveInBigQuery(String platform, String shopId, BlobIdWrapper blobIdWrapper) {
        try {
            final String dataset = DatabaseUtils.getDatasetName(platform, shopId);
            TableId tableId = TableId.of(dataset, ETLUtils.TABLE_SHOP_PERFORMANCE);
            LoadJobConfiguration loadConfig =
                    LoadJobConfiguration.newBuilder(tableId, blobIdWrapper.uri)
                            .setFormatOptions(FormatOptions.json())
                            .setCreateDisposition(JobInfo.CreateDisposition.CREATE_NEVER)
                            .setWriteDisposition(JobInfo.WriteDisposition.WRITE_APPEND)
                            .build();

            Job job = bigquery.create(JobInfo.of(loadConfig));
            job = job.waitFor();

            if ( job.isDone() ) {
                log.info("Successfully save shop performance");
                return true;
            } else {
                log.error("Failed to save shop performance: " + job.getStatus().getError());
            }
        } catch (InterruptedException e) {
            log.error("Failed to save shop performance: " + e.getMessage());
        }
        return false;
    }

}
