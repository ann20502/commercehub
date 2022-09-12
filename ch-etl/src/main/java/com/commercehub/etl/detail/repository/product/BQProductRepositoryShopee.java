package com.commercehub.etl.detail.repository.product;

import com.commercehub.common.DatabaseUtils;
import com.commercehub.common.StorageUtils;
import com.commercehub.common.Utils;
import com.commercehub.etl.core.entity.product.ItemShopee;
import com.commercehub.etl.core.entitybuilder.product.shopee.ItemShopeeBuilder;
import com.commercehub.etl.core.repository.ProductRepositoryShopee;
import com.commercehub.etl.detail.common.BQUtils;
import com.commercehub.etl.detail.common.ETLUtils;
import com.commercehub.etl.detail.repository.BlobIdWrapper;
import com.commercehub.etl.detail.repository.product.transformer.BQItemShopeeTransformer;
import com.commercehub.gcp.core.entity.CloudStorageConfiguration;
import com.commercehub.gcp.core.entity.ProjectConfiguration;
import com.commercehub.gcp.core.usecase.GetCloudStorageConfiguration;
import com.commercehub.gcp.core.usecase.GetProjectConfiguration;
import com.google.cloud.bigquery.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Dependent
public class BQProductRepositoryShopee implements ProductRepositoryShopee {

    @Inject
    Logger log;

    @Inject
    BigQuery bigquery;

    @Inject
    GetProjectConfiguration getProjectConfiguration;

    @Inject
    Storage storage;

    @Inject
    GetCloudStorageConfiguration getCloudStorageConfiguration;

    @Override
    public List<ItemShopee> getItemWithLatestUpdateTimestamp(String platform, String shopId, List<Long> itemIds) {
        ProjectConfiguration configuration = getProjectConfiguration.execute();
        final String DATASET = DatabaseUtils.getDatasetName(platform, shopId);
        final String TABLE_NAME = BQUtils.getTableName(configuration.projectId, DATASET, "item");
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
                    .map(valueList -> new ItemShopeeBuilder()
                            .setItemId(valueList.get("item_id").getLongValue())
                            .setUpdateTime(Instant.ofEpochMilli(valueList.get("update_time").getTimestampValue() / 1000L))
                            .createItemShopee()
                    )
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            log.error("Failed to retrieve item with latest update timestamp: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve item with latest update timestamp: " + e.getMessage());
        }
    }

    @Override
    public boolean saveItemUpdate(String platform, String shopId, List<ItemShopee> items) {
        List<BQItemShopee> bqItems = transform(items);
        BlobIdWrapper blobIdWrapper = saveInCloudStorage(bqItems);
        if ( !saveInBigQuery(platform, shopId, blobIdWrapper) ) {
            return false;
        }

        if ( !storage.delete(blobIdWrapper.blobId) ) {
            log.error("Failed to delete item with update file in cloud storage");
        }

        return true;
    }

    private List<BQItemShopee> transform(List<ItemShopee> items) {
        List<BQItemShopee> result = new ArrayList<>();
        for ( ItemShopee item : items ) {
            result.add(BQItemShopeeTransformer.from(item));
        }
        return result;
    }

    private BlobIdWrapper saveInCloudStorage(List<BQItemShopee> items) {
        final String json = Utils.toJson(BQItemShopee.class, items);
        CloudStorageConfiguration configuration = getCloudStorageConfiguration.execute();
        BlobId blobId = BlobId.of(configuration.bucketETL, getObjectName());
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
