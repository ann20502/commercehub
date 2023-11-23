package com.commercehub.rs.detail.repository;

import com.commercehub.gcp.core.entity.ProjectConfiguration;
import com.commercehub.gcp.core.usecase.GetProjectConfiguration;
import com.commercehub.rs.core.entity.ShopPerformance;
import com.commercehub.rs.core.entitybuilder.ShopPerformanceBuilder;
import com.commercehub.rs.core.repository.ShopPerformanceRepository;
import com.commercehub.rs.detail.common.BQUtils;
import com.google.cloud.bigquery.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.Instant;
import java.util.stream.StreamSupport;

@Dependent
public class BQShopPerformanceRepositoryShopee implements ShopPerformanceRepository {

    private final String TABLE_NAME_SHOP_PERFORMANCE = "shop_performance";

    @Inject
    Logger log;

    @Inject
    BigQuery bigquery;

    @Inject
    GetProjectConfiguration getProjectConfiguration;

    @Override
    public ShopPerformance execute(String platform, String shopId) {
        ProjectConfiguration configuration = getProjectConfiguration.execute();
        final String dataset = BQUtils.getDatasetName(platform, shopId);
        final String TABLE_NAME = BQUtils.getTableName(configuration.projectId, dataset, TABLE_NAME_SHOP_PERFORMANCE);
        final String QUERY =
                "SELECT p1.extract_time, overall_performance, fulfillment_preparation_time, cservice_response_time, csatisfaction_rating_overall "
                        + "FROM `" + TABLE_NAME + "` p1 "
                        + "JOIN "
                        + "( "
                        + "    SELECT MAX(extract_time) AS extract_time "
                        + "    FROM `" + TABLE_NAME + "` "
                        + ") p2 ON p1.extract_time = p2.extract_time "
                        + "LIMIT 1 ";

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(QUERY)
                .build();

        try {
            TableResult results = bigquery.query(queryConfig);
            return StreamSupport.stream(results.getValues().spliterator(), false)
                    .map(valueList -> {
                        ShopPerformance.Data preparation = null;
                        FieldValue preparationTimeWrapper = valueList.get("fulfillment_preparation_time");
                        if ( !preparationTimeWrapper.isNull() ) {
                            FieldValueList preparationTime =  preparationTimeWrapper.getRecordValue();
                            preparation = extractData(preparationTime);
                        }

                        ShopPerformance.Data response = null;
                        FieldValue responseTimeWrapper = valueList.get("cservice_response_time");
                        if ( !responseTimeWrapper.isNull() ) {
                            FieldValueList responseTime = responseTimeWrapper.getRecordValue();
                            response = extractData(responseTime);
                        }

                        ShopPerformance.Data rating = null;
                        FieldValue ratingOverallWrapper = valueList.get("csatisfaction_rating_overall");
                        if ( !ratingOverallWrapper.isNull() ) {
                            FieldValueList ratingOverall = ratingOverallWrapper.getRecordValue();
                            rating = extractData(ratingOverall);
                        }

                        return new ShopPerformanceBuilder()
                                .setExtractTime(Instant.ofEpochMilli(valueList.get("extract_time").getTimestampValue() / 1000L))
                                .setOverallPerformance(valueList.get("overall_performance").getLongValue())
                                .setRating(rating)
                                .setFulfillmentPreparationTime(preparation)
                                .setChatResponseTime(response)
                                .createShopPerformance();
                    })
                    .findFirst()
                    .orElse(new ShopPerformanceBuilder().createShopPerformance());
        } catch (InterruptedException e) {
            log.error("Failed to retrieve shop performance: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve shop performance: " + e.getMessage());
        }
    }

    private ShopPerformance.Data extractData(FieldValueList fieldValueList) {
        FieldValue target = fieldValueList.get("target");
        FieldValue performance = fieldValueList.get("performance");
        FieldValue penaltyPoint = fieldValueList.get("penalty_point");
        return new ShopPerformance.Data(
                target.isNull() ? "" : target.getStringValue(),
                performance.isNull() ? "" : performance.getStringValue(),
                penaltyPoint.isNull() ? "" : penaltyPoint.getStringValue()
        );
    }

}
