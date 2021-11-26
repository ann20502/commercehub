package com.commercehub.linker.domain.usecase;

import com.commercehub.linker.domain.repository.LinkingRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Map;

@Dependent
public class SetupLinking {

    @Inject
    LinkingRepository repository;

    public Uni<Boolean> execute(String documentId, Map<String,Object> values) {
        return Uni.createFrom().item(() -> repository.update(documentId, values));
    }

}
