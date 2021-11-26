package com.commercehub.etl.domain.repository;

import com.google.api.gax.paging.Page;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.Dataset;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Dependent
public class BQDataRepository implements DataRepository {

    @Inject
    BigQuery bigquery;

    @Override
    public List<String> getAllDataset() {
        Page<Dataset> datasets = bigquery.listDatasets(BigQuery.DatasetListOption.all());
        return StreamSupport.stream(datasets.iterateAll().spliterator(), false)
                .map(dataset -> dataset.getDatasetId().getDataset())
                .collect(Collectors.toList());
    }

}
