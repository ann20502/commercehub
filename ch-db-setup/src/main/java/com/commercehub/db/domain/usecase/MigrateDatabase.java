package com.commercehub.db.domain.usecase;

import com.commercehub.common.DatabaseUtils;
import com.commercehub.db.configuration.FlywayConfigurations;
import com.commercehub.db.domain.entity.Linking;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;
import org.flywaydb.core.api.output.RepairResult;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class MigrateDatabase {

    @Inject
    Logger log;

    @Inject
    FlywayConfigurations flywayConfigurations;

    public boolean migrate(Linking linking) {
        String datasetName = DatabaseUtils.getDatasetName(linking.getPlatform(), linking.getShopId());
        String url = flywayConfigurations.getUrl(datasetName);

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
