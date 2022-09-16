package com.commercehub.linker.detail.usecase;

import com.commercehub.linker.core.repository.LinkingRepository;
import com.commercehub.linker.core.usecase.SetupLinking;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import java.util.Map;

@Dependent
public class SetupLinkingInteractor implements SetupLinking {

    private final LinkingRepository repository;

    public SetupLinkingInteractor(LinkingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Uni<Boolean> setup(String id, Map<String, Object> fieldToSetup) {
        return Uni.createFrom().item(() -> repository.update(id, fieldToSetup));
    }

}
