package com.commercehub.etl.domain.usecase.dataset;

import com.commercehub.etl.domain.repository.DataRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class GetDataset {

    @Inject
    DataRepository repository;

    public Uni<List<String>> getAll() {
        return Uni.createFrom().item(repository.getAllDataset());
    }

}
