package com.commercehub.etl.domain.usecase.linking;

import com.commercehub.etl.domain.entity.Linking;
import com.commercehub.etl.domain.repository.LinkingRepository;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GetLinking {

    @Inject
    LinkingRepository repository;

    public Multi<Linking> getAllWithTokenExpired(String status) {
        final int FAST_FORWARD_MINUTES = 30;
        return Multi.createFrom().iterable(repository.getAllWithTokenExpired(status, FAST_FORWARD_MINUTES));
    }

}
