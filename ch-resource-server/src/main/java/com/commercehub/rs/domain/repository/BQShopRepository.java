package com.commercehub.rs.domain.repository;

import com.commercehub.configuration.GCPConfigurations;
import com.commercehub.rs.domain.entity.ShopPerformance;
import com.commercehub.rs.utils.BQUtils;
import com.google.cloud.bigquery.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.Instant;
import java.util.stream.StreamSupport;

@Dependent
public class BQShopRepository implements ShopRepository {

    @Inject
    Logger log;

    @Inject
    BigQuery bigquery;

    @Inject
    GCPConfigurations configurations;

    @Override
    public ShopPerformance getShopPerformance(String dataset) {
        final String TABLE_NAME = BQUtils.getTableName(configurations.getProjectId(), dataset, "shop_performance");
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
                            preparation = new ShopPerformance.Data(
                                    preparationTime.get("target").getStringValue(),
                                    preparationTime.get("performance").getStringValue(),
                                    preparationTime.get("penalty_point").getStringValue()
                            );
                        }

                        ShopPerformance.Data response = null;
                        FieldValue responseTimeWrapper = valueList.get("cservice_response_time");
                        if ( !responseTimeWrapper.isNull() ) {
                            FieldValueList responseTime = responseTimeWrapper.getRecordValue();
                            response = new ShopPerformance.Data(
                                    responseTime.get("target").getStringValue(),
                                    responseTime.get("performance").getStringValue(),
                                    responseTime.get("penalty_point").getStringValue()
                            );
                        }

                        ShopPerformance.Data rating = null;
                        FieldValue ratingOverallWrapper = valueList.get("csatisfaction_rating_overall");
                        if ( !ratingOverallWrapper.isNull() ) {
                            FieldValueList ratingOVerall = ratingOverallWrapper.getRecordValue();
                            rating = new ShopPerformance.Data(
                                    ratingOVerall.get("target").getStringValue(),
                                    ratingOVerall.get("performance").getStringValue(),
                                    ratingOVerall.get("penalty_point").getStringValue()
                            );
                        }

                        return new ShopPerformance(
                                Instant.ofEpochMilli(valueList.get("extract_time").getTimestampValue() / 1000L),
                                valueList.get("overall_performance").getLongValue(),
                                rating,
                                preparation,
                                response
                        );
                    })
                    .findFirst().orElse(new ShopPerformance(null, 0L, null, null, null));
        } catch (InterruptedException e) {
            log.error("Failed to retrieve shop performance: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve shop performance: " + e.getMessage());
        }
    }

}
