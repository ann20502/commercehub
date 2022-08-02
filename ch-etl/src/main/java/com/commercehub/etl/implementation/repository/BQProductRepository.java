package com.commercehub.etl.implementation.repository;

import com.commercehub.common.DatabaseUtils;
import com.commercehub.common.StorageUtils;
import com.commercehub.common.Utils;
import com.commercehub.configuration.CloudStorageConfigurations;
import com.commercehub.configuration.GCPConfigurations;
import com.commercehub.etl.common.BQUtils;
import com.commercehub.etl.common.ETLUtils;
import com.commercehub.etl.domain.entity.product.Item;
import com.commercehub.etl.domain.entity.product.ItemBuilder;
import com.commercehub.etl.domain.repository.BlobIdWrapper;
import com.commercehub.etl.domain.repository.ProductRepository;
import com.google.cloud.bigquery.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Dependent
public class BQProductRepository implements ProductRepository {

    @Inject
    Logger log;

    @Inject
    BigQuery bigquery;

    @Inject
    Storage storage;

    @Inject
    GCPConfigurations gcpConfigurations;

    @Inject
    CloudStorageConfigurations csConfigurations;

    @Override
    public List<Item> getItemWithLatestUpdateTimestamp(String platform, String shopId, List<Long> itemIds) {
        final String DATASET = DatabaseUtils.getDatasetName(platform, shopId);
        final String TABLE_NAME = BQUtils.getTableName(gcpConfigurations.getProjectId(), DATASET, "item");
        final String QUERY =
                "SELECT item_id, update_time " +
                        "FROM " +
                        "( " +
                        "   SELECT ARRAY_AGG(i ORDER BY i.update_time DESC LIMIT 1)[OFFSET(0)].* " +
                        "   FROM `" + TABLE_NAME + "` i " +
                        "   GROUP BY i.item_id " +
                        ") " +
                        "WHERE item_id IN UNNEST(@ids)";

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(QUERY)
                .addNamedParameter("ids", QueryParameterValue.array(itemIds.toArray(), StandardSQLTypeName.INT64))
                .build();

        try {
            TableResult results = bigquery.query(queryConfig);
            return StreamSupport.stream(results.getValues().spliterator(), false)
                    .map(valueList -> new ItemBuilder()
                            .setItemId(valueList.get("item_id").getLongValue())
                            .setUpdateTime(Instant.ofEpochMilli(valueList.get("update_time").getTimestampValue() / 1000L))
                            .createItem()
                    )
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            log.error("Failed to retrieve item with latest update timestamp: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve item with latest update timestamp: " + e.getMessage());
        }
    }

    @Override
    public boolean saveItemUpdate(String platform, String shopId, List<Item> items) {
        BlobIdWrapper blobIdWrapper = saveInCloudStorage(items);
        if ( !saveInBigQuery(platform, shopId, blobIdWrapper) ) {
            return false;
        }

        if ( !storage.delete(blobIdWrapper.blobId) ) {
            log.error("Failed to delete item with update file in cloud storage");
        }

        return true;
    }

    private BlobIdWrapper saveInCloudStorage(List<Item> items) {
        final String json = Utils.toJson(Item.class, items);
        BlobId blobId = BlobId.of(csConfigurations.bucket(), getObjectName());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, json.getBytes(StandardCharsets.UTF_8), Storage.BlobTargetOption.doesNotExist());
        return new BlobIdWrapper(blobId, StorageUtils.getFullPath(blobId.getBucket(), blobId.getName()));
    }

    private String getObjectName() {
        return "item-" + System.currentTimeMillis();
    }

    private boolean saveInBigQuery(String platform, String shopId, BlobIdWrapper blobIdWrapper) {
        try {
            final String dataset = DatabaseUtils.getDatasetName(platform, shopId);
            TableId tableId = TableId.of(dataset, ETLUtils.TABLE_ITEM);
            LoadJobConfiguration loadConfig =
                    LoadJobConfiguration.newBuilder(tableId, blobIdWrapper.uri)
                            .setFormatOptions(FormatOptions.json())
                            .setCreateDisposition(JobInfo.CreateDisposition.CREATE_NEVER)
                            .setWriteDisposition(JobInfo.WriteDisposition.WRITE_APPEND)
                            .build();

            Job job = bigquery.create(JobInfo.of(loadConfig));
            job = job.waitFor();

            if ( job.isDone() ) {
                log.info("Successfully added item update");
                return true;
            } else {
                log.error("Failed to add item update: " + job.getStatus().getError());
            }
        } catch (InterruptedException e) {
            log.error("Failed to add item update: " + e.getMessage());
        }
        return false;
    }

}
