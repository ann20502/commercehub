package com.commercehub.etl.domain.repository;

import com.commercehub.common.DatabaseUtils;
import com.commercehub.common.StorageUtils;
import com.commercehub.common.Utils;
import com.commercehub.etl.common.ETLUtils;
import com.commercehub.etl.domain.entity.order.Order;
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
import java.util.List;

@Dependent
public class BQOrderRepository implements OrderRepository {

    @Inject
    BigQuery bigquery;

    @Inject
    Storage storage;

    @Inject
    Logger log;

    @Override
    public Uni<Boolean> add(String platform, String shopId, List<Order> orders) {
        return Multi.createFrom().item(orders)
                .map(this::saveInCloudStorage)
                .filter(blobIdWrapper -> saveInBigQuery(platform, shopId, blobIdWrapper))
                .filter(blobIdWrapper -> storage.delete(blobIdWrapper.blobId)) // Can be done by separate job
                .toUni()
                .map(blobIdWrapper -> Boolean.TRUE);
    }

    private BlobIdWrapper saveInCloudStorage(List<Order> orders) {
        final String json = Utils.toJson(Order.class, orders);
        BlobId blobId = BlobId.of(ETLUtils.BUCKET, getObjectName());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, json.getBytes(StandardCharsets.UTF_8), Storage.BlobTargetOption.doesNotExist());
        return new BlobIdWrapper(blobId, StorageUtils.getFullPath(blobId.getBucket(), blobId.getName()));
    }

    private String getObjectName() {
        return "shopee-new-order-" + System.currentTimeMillis();
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
