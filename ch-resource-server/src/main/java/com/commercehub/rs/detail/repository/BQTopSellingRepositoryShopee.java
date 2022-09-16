package com.commercehub.rs.detail.repository;

import com.commercehub.gcp.core.entity.ProjectConfiguration;
import com.commercehub.gcp.core.usecase.GetProjectConfiguration;
import com.commercehub.rs.core.entity.TopSelling;
import com.commercehub.rs.core.entity.input.TopSellingInput;
import com.commercehub.rs.core.entitybuilder.TopSellingItemBuilder;
import com.commercehub.rs.core.repository.TopSellingRepository;
import com.commercehub.rs.detail.common.BQUtils;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryParameterValue;
import com.google.cloud.bigquery.TableResult;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Dependent
public class BQTopSellingRepositoryShopee implements TopSellingRepository {

    private final String TABLE_NAME_ORDER = "order_create_time";

    @Inject
    Logger log;

    @Inject
    BigQuery bigquery;

    @Inject
    GetProjectConfiguration getProjectConfiguration;

    @Override
    public TopSelling execute(TopSellingInput input, int limit) {
        final String dataset = BQUtils.getDatasetName(input.platform, input.shopId);
        final ProjectConfiguration configuration = getProjectConfiguration.execute();
        final String TABLE_NAME = BQUtils.getTableName(configuration.projectId, dataset, TABLE_NAME_ORDER);

        final String QUERY =
                "SELECT item_id, item_name, model_name, image_url, SUM(model_quantity_purchased) AS quantity_sold "
                        + "FROM "
                        + "( "
                        + "    SELECT DISTINCT o1.* EXCEPT(recipient_address, items, invoice_data, escrow), i.item_id, i.item_name, i.model_name, i.image_info.image_url, i.model_quantity_purchased "
                        + "    FROM `" + TABLE_NAME + "` o1, UNNEST(o1.items) i "
                        + "    JOIN "
                        + "    ( "
                        + "        SELECT order_sn, MAX(extract_time) extract_time "
                        + "        FROM `" + TABLE_NAME + "` "
                        + "        WHERE DATE(create_time, @zone) BETWEEN @dateFrom AND @dateTo "
                        + "        GROUP BY order_sn "
                        + "    ) o2 ON o1.order_sn = o2.order_sn AND o1.extract_time = o2.extract_time AND o1.order_status != 'CANCELLED' "
                        + ") "
                        + "GROUP BY item_id, item_name, model_name, image_url "
                        + "ORDER BY quantity_sold DESC "
                        + "LIMIT @limit ";

        String strFrom = BQUtils.localDateToString(input.from);
        String strTo = BQUtils.localDateToString(input.to);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(QUERY)
                .addNamedParameter("dateFrom", QueryParameterValue.date(strFrom))
                .addNamedParameter("dateTo", QueryParameterValue.date(strTo))
                .addNamedParameter("zone", QueryParameterValue.string(input.zone))
                .addNamedParameter("limit", QueryParameterValue.int64(limit))
                .build();

        try {
            TableResult results = bigquery.query(queryConfig);
            return StreamSupport.stream(results.getValues().spliterator(), false)
                    .map(valueList ->
                            new TopSellingItemBuilder()
                                    .setItemId(valueList.get("item_id").getLongValue())
                                    .setItemName(valueList.get("item_name").getStringValue())
                                    .setModelName(valueList.get("model_name").getStringValue())
                                    .setImageUrl(valueList.get("image_url").getStringValue())
                                    .setSold(valueList.get("quantity_sold").getLongValue())
                                    .createItem()
                    )
                    .collect(Collectors.collectingAndThen(Collectors.toList(), TopSelling::new));
        } catch (InterruptedException e) {
            log.error("Failed to retrieve top selling: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve top selling: " + e.getMessage());
        }
    }

}
