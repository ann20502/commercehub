package com.commercehub.linker.domain.usecase;

import com.commercehub.linker.domain.entity.Linking;
import com.commercehub.linker.domain.repository.LinkingRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GetExistingLinking {

    @Inject
    LinkingRepository repository;

    public Multi<Linking> getAll(String status) {
        return Uni.createFrom().item(status)
                .onItem().transformToMulti(input -> Multi.createFrom().iterable(repository.getAll(input)));
    }

}
