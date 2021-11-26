package com.commercehub.etl.domain.usecase.linking;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.repository.LinkingRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class GetLinking {

    @Inject
    LinkingRepository repository;

    public Uni<List<Linking>> getAll(String status) {
        return Uni.createFrom().item(repository.getAll(status));
    }

    public Uni<List<Linking>> getAll(String status, boolean hasSetup) {
        return Uni.createFrom().item(repository.getAll(status, hasSetup));
    }

    public Uni<List<Linking>> getAll(String status, boolean hasSetup, boolean hasLink) {
        return Uni.createFrom().item(repository.getAll(status, hasSetup, hasLink));
    }

    public Multi<Linking> getAllWithTokenExpired() {
        final int FAST_FORWARD_MINUTES = 30;
        return Multi.createFrom().iterable(
                repository.getAllWithTokenExpired(Linking.STATUS_ACTIVE, FAST_FORWARD_MINUTES)
        );
    }

}
