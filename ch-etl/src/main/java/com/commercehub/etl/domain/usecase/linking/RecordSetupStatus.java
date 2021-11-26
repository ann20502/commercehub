package com.commercehub.etl.domain.usecase.linking;

import com.commercehub.etl.domain.repository.LinkingRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class RecordSetupStatus {

    @Inject
    LinkingRepository repository;

    public boolean record(String documentId, boolean setupResult) {
        return repository.updateSetup(documentId, setupResult);
    }

}
