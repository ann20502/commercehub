package com.commercehub.etl.domain.usecase.dataset;

import com.commercehub.etl.common.ETLUtils;
import com.commercehub.etl.configuration.FlywayConfiguration;
import com.commercehub.etl.domain.entity.linking.Linking;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.BaselineResult;
import org.flywaydb.core.api.output.MigrateResult;
import org.flywaydb.core.api.output.RepairResult;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class CreateDataset {

    @Inject
    Logger log;

    @Inject
    FlywayConfiguration flywayConfiguration;

    public boolean create(Linking linking) {
        String datasetName = ETLUtils.getDatasetName(linking.getPlatform(), linking.getShopId());
        String url = flywayConfiguration.getUrl(datasetName);

        log.info("===== Adjusting schema for dataset [" + datasetName + "] =====");
        log.info("Big Query JDBC Url: " + url);

        Flyway flyway = Flyway.configure().dataSource(url, "", "")
                .schemas(datasetName)
                .load();

        RepairResult repairResult = flyway.repair();
        log.info("Database schema repair result: " + repairResult.toString());

        MigrateResult result = flyway.migrate();
        log.info("Database schema migration result: " + result.toString());

        return true;
    }

}
