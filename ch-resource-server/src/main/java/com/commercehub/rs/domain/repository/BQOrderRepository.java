package com.commercehub.rs.domain.repository;

import com.commercehub.configuration.GCPConfigurations;
import com.commercehub.rs.domain.entity.SalesByCalendar;
import com.commercehub.rs.domain.entity.TopSelling;
import com.commercehub.rs.utils.BQUtils;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryParameterValue;
import com.google.cloud.bigquery.TableResult;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Dependent
public class BQOrderRepository implements OrderRepository {

    @Inject
    Logger log;

    @Inject
    BigQuery bigquery;

    @Inject
    GCPConfigurations configurations;

    @Override
    public List<SalesByCalendar> getSalesByMonth(String dataset, LocalDate from, LocalDate to, String zone) {
        final String TABLE_NAME = BQUtils.getTableName(configurations.getProjectId(), dataset, "order_create_time");
        final String QUERY =
                "SELECT DATE_TRUNC(DATE(create_time, @zone), MONTH) AS create_year_month, SUM(total_amount) AS total "
                        + "FROM "
                        + "( "
                        + "    SELECT DISTINCT o1.order_sn, o1.create_time, o1.total_amount "
                        + "    FROM `" + TABLE_NAME + "` o1 "
                        + "    JOIN "
                        + "    ( "
                        + "        SELECT order_sn, MAX(extract_time) extract_time "
                        + "        FROM `" + TABLE_NAME + "` "
                        + "        WHERE DATE(create_time, @zone) BETWEEN @dateFrom AND @dateTo "
                        + "        GROUP BY order_sn "
                        + "    ) o2 ON o1.order_sn = o2.order_sn AND o1.extract_time = o2.extract_time "
                        + ") "
                        + "GROUP BY DATE_TRUNC(DATE(create_time, @zone), MONTH) ";

        String strFrom = BQUtils.localDateToString(from);
        String strTo = BQUtils.localDateToString(to);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(QUERY)
                .addNamedParameter("dateFrom", QueryParameterValue.date(strFrom))
                .addNamedParameter("dateTo", QueryParameterValue.date(strTo))
                .addNamedParameter("zone", QueryParameterValue.string(zone))
                .build();

        try {
            TableResult results = bigquery.query(queryConfig);
            return StreamSupport.stream(results.getValues().spliterator(), false)
                    .map(valueList -> new SalesByCalendar(
                            BQUtils.stringToLocalDate(valueList.get("create_year_month").getStringValue()),
                            valueList.get("total").getNumericValue()
                    ))
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            log.error("Failed to retrieve sales by month: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve sales by month: " + e.getMessage());
        }
    }

    @Override
    public List<SalesByCalendar> getSalesByDate(String dataset, LocalDate from, LocalDate to, String zone) {
        final String TABLE_NAME = BQUtils.getTableName(configurations.getProjectId(), dataset, "order_create_time");
        final String QUERY =
                "SELECT DATE(o1.create_time, @zone) AS create_date, SUM(o1.total_amount) AS total " +
                        "FROM `" + TABLE_NAME + "` o1 " +
                        "JOIN " +
                        "( " +
                        "    SELECT order_sn, MAX(extract_time) extract_time " +
                        "    FROM `" + TABLE_NAME + "` " +
                        "    WHERE DATE(create_time, @zone) BETWEEN @dateFrom AND @dateTo " +
                        "    GROUP BY order_sn " +
                        ") o2 ON o1.order_sn = o2.order_sn AND o1.extract_time = o2.extract_time " +
                        "GROUP BY DATE(o1.create_time, @zone) ";

        String strFrom = BQUtils.localDateToString(from);
        String strTo = BQUtils.localDateToString(to);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(QUERY)
                .addNamedParameter("dateFrom", QueryParameterValue.date(strFrom))
                .addNamedParameter("dateTo", QueryParameterValue.date(strTo))
                .addNamedParameter("zone", QueryParameterValue.string(zone))
                .build();

        try {
            TableResult results = bigquery.query(queryConfig);
            return StreamSupport.stream(results.getValues().spliterator(), false)
                    .map(valueList -> new SalesByCalendar(
                            BQUtils.stringToLocalDate(valueList.get("create_date").getStringValue()),
                            valueList.get("total").getNumericValue()
                    ))
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            log.error("Failed to retrieve sales by date: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve sales by date: " + e.getMessage());
        }
    }

    @Override
    public TopSelling getTopSelling(String dataset, LocalDate from, LocalDate to, String zone) {
        final String TABLE_NAME = BQUtils.getTableName(configurations.getProjectId(), dataset, "order_create_time");
        final String QUERY =
                "SELECT i.item_id, i.item_name, i.model_name, i.image_info.image_url, SUM(i.model_quantity_purchased) AS quantity_sold " +
                        "FROM `" + TABLE_NAME + "` o1, UNNEST(o1.items) i " +
                        "JOIN " +
                        "( " +
                        "   SELECT order_sn, MAX(extract_time) extract_time " +
                        "   FROM `" + TABLE_NAME + "` " +
                        "   WHERE DATE(create_time, @zone) BETWEEN @dateFrom AND @dateTo " +
                        "   GROUP BY order_sn " +
                        ") o2 ON o1.order_sn = o2.order_sn AND o1.extract_time = o2.extract_time AND o1.order_status != 'CANCELLED' " +
                        "GROUP BY i.item_id, i.item_name, i.model_name, i.image_info.image_url " +
                        "ORDER BY quantity_sold DESC " +
                        "LIMIT 5 ";

        String strFrom = BQUtils.localDateToString(from);
        String strTo = BQUtils.localDateToString(to);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(QUERY)
                .addNamedParameter("dateFrom", QueryParameterValue.date(strFrom))
                .addNamedParameter("dateTo", QueryParameterValue.date(strTo))
                .addNamedParameter("zone", QueryParameterValue.string(zone))
                .build();

        try {
            TableResult results = bigquery.query(queryConfig);
            return StreamSupport.stream(results.getValues().spliterator(), false)
                    .map(valueList -> new TopSelling.Item(
                            valueList.get("item_id").getLongValue(),
                            valueList.get("item_name").getStringValue(),
                            valueList.get("model_name").getStringValue(),
                            valueList.get("image_url").getStringValue(),
                            valueList.get("quantity_sold").getLongValue()
                    ))
                    .collect(Collectors.collectingAndThen(Collectors.toList(), TopSelling::new));
        } catch (InterruptedException e) {
            log.error("Failed to retrieve top selling: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve top selling: " + e.getMessage());
        }
    }

}
