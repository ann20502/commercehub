package com.commercehub.rs.detail.repository;

import com.commercehub.gcp.core.entity.ProjectConfiguration;
import com.commercehub.gcp.core.usecase.GetProjectConfiguration;
import com.commercehub.rs.core.entity.SaleByMonth;
import com.commercehub.rs.core.entity.input.GetSaleInput;
import com.commercehub.rs.core.repository.SaleByMonthRepository;
import com.commercehub.rs.detail.common.BQUtils;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryParameterValue;
import com.google.cloud.bigquery.TableResult;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Dependent
public class BQSaleByMonthRepositoryShopee implements SaleByMonthRepository {

    private final String TABLE_NAME_ORDER = "order_create_time";

    @Inject
    Logger log;

    @Inject
    BigQuery bigquery;

    @Inject
    GetProjectConfiguration getProjectConfiguration;

    @Override
    public List<SaleByMonth> execute(GetSaleInput input) {
        ProjectConfiguration projectConfiguration = getProjectConfiguration.execute();
        final String dataset = BQUtils.getDatasetName(input.platform, input.shopId);
        final String TABLE_NAME = BQUtils.getTableName(projectConfiguration.projectId, dataset, TABLE_NAME_ORDER);
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

        String strFrom = BQUtils.localDateToString(input.from);
        String strTo = BQUtils.localDateToString(input.to);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(QUERY)
                .addNamedParameter("dateFrom", QueryParameterValue.date(strFrom))
                .addNamedParameter("dateTo", QueryParameterValue.date(strTo))
                .addNamedParameter("zone", QueryParameterValue.string(input.zone))
                .build();

        try {
            TableResult results = bigquery.query(queryConfig);
            return StreamSupport.stream(results.getValues().spliterator(), false)
                    .map(valueList -> new SaleByMonth(
                            BQUtils.stringToYearMonth(valueList.get("create_year_month").getStringValue()),
                            valueList.get("total").getNumericValue()
                    ))
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            log.error("Failed to retrieve sales by month: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve sales by month: " + e.getMessage());
        }
    }

}
