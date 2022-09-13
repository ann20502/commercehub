package com.commercehub.db.detail.usecase;

import com.commercehub.common.DatabaseUtils;
import com.commercehub.db.core.entity.Linking;
import com.commercehub.db.core.usecase.CreateOrUpdateDatabase;
import com.commercehub.db.core.usecase.GetFlywayUrl;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;
import org.flywaydb.core.api.output.RepairResult;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class CreateOrUpdateDatabaseInteractor implements CreateOrUpdateDatabase {

    @Inject
    Logger log;

    @Inject
    GetFlywayUrl getFlywayUrl;

    @Override
    public boolean execute(Linking linking) {
        String datasetName = DatabaseUtils.getDatasetName(linking.platform, linking.shopId);
        String url = getFlywayUrl.execute(datasetName);

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
