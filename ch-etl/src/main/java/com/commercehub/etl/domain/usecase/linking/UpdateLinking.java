package com.commercehub.etl.domain.usecase.linking;

import com.commercehub.etl.domain.entity.Linking;
import com.commercehub.etl.domain.repository.LinkingRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class UpdateLinking {

    @Inject
    LinkingRepository repository;

    public boolean execute(Linking linking) {
        return repository.updateToken(linking);
    }

}
