package com.commercehub.etl.detail.repository.order;

import com.commercehub.common.DatabaseUtils;
import com.commercehub.common.StorageUtils;
import com.commercehub.common.Utils;
import com.commercehub.etl.core.entity.order.OrderShopee;
import com.commercehub.etl.core.repository.OrderRepositoryShopee;
import com.commercehub.etl.detail.common.ETLUtils;
import com.commercehub.etl.detail.repository.BlobIdWrapper;
import com.commercehub.etl.detail.repository.order.transformer.BQOrderShopeeTransformer;
import com.commercehub.gcp.core.entity.CloudStorageConfiguration;
import com.commercehub.gcp.core.usecase.GetCloudStorageConfiguration;
import com.google.cloud.bigquery.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class BQOrderRepositoryShopee implements OrderRepositoryShopee {

    @Inject
    Logger log;

    @Inject
    BigQuery bigquery;

    @Inject
    Storage storage;

    @Inject
    GetCloudStorageConfiguration getCloudStorageConfiguration;

    @Override
    public boolean add(String platform, String shopId, List<OrderShopee> orders) {
        List<BQOrderShopee> bqOrders = transform(orders);
        BlobIdWrapper blobIdWrapper = saveInCloudStorage(bqOrders);
        if ( !saveInBigQuery(platform, shopId, blobIdWrapper) ) {
            return false;
        }

        if ( !storage.delete(blobIdWrapper.blobId) ) {
            log.error("Failed to delete shopee order file in cloud storage");
        }

        return true;
    }

    private List<BQOrderShopee> transform(List<OrderShopee> orders) {
        List<BQOrderShopee> result = new ArrayList<>();
        for ( OrderShopee order : orders ) {
            result.add(BQOrderShopeeTransformer.from(order));
        }
        return result;
    }

    private BlobIdWrapper saveInCloudStorage(List<BQOrderShopee> orders) {
        final String json = Utils.toJson(BQOrderShopee.class, orders);
        CloudStorageConfiguration configuration = getCloudStorageConfiguration.execute();
        BlobId blobId = BlobId.of(configuration.bucketETL, getObjectName());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, json.getBytes(StandardCharsets.UTF_8), Storage.BlobTargetOption.doesNotExist());
        return new BlobIdWrapper(blobId, StorageUtils.getFullPath(blobId.getBucket(), blobId.getName()));
    }

    private String getObjectName() {
        return "order-" + System.currentTimeMillis();
    }

    private boolean saveInBigQuery(String platform, String shopId, BlobIdWrapper blobIdWrapper) {
        try {
            final String dataset = DatabaseUtils.getDatasetName(platform, shopId);
            TableId tableId = TableId.of(dataset, ETLUtils.TABLE_ORDER_CREATE_TIME);
            LoadJobConfiguration loadConfig =
                    LoadJobConfiguration.newBuilder(tableId, blobIdWrapper.uri)
                            .setFormatOptions(FormatOptions.json())
                            .setCreateDisposition(JobInfo.CreateDisposition.CREATE_NEVER)
                            .setWriteDisposition(JobInfo.WriteDisposition.WRITE_APPEND)
                            .build();

            Job job = bigquery.create(JobInfo.of(loadConfig));
            job = job.waitFor();

            if ( job.isDone() ) {
                log.info("Successfully added orders");
                return true;
            } else {
                log.error("Failed to add order: " + job.getStatus().getError());
            }
        } catch (InterruptedException e) {
            log.error("Failed to add order: " + e.getMessage());
        }
        return false;
    }

}
