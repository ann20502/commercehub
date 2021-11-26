package com.commercehub.linker.domain.usecase;

import com.commercehub.linker.configuration.ApplicationConfiguration;
import com.commercehub.linker.domain.entity.ExistingLinking;
import com.commercehub.linker.domain.repository.LinkingRepository;
import com.commercehub.linker.utils.LinkerUtils;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GetExistingLinking {

    @Inject
    ApplicationConfiguration configuration;

    @Inject
    LinkingRepository repository;

    public Multi<ExistingLinking> getAll(boolean link) {
        return Uni.createFrom().item(link)
                .onItem().transformToMulti(input -> Multi.createFrom().iterable(repository.getAll(input)))
                .map(ExistingLinking::new)
                .map(linking -> {
                    linking.setLogo(LinkerUtils.getImagePath(configuration.rootPath(), linking.getPlatform()));
                    return linking;
                });
    }

    public Uni<ExistingLinking> getById(String documentId) {
        return Uni.createFrom().item(documentId)
                .map(input -> repository.get(input))
                .map(linking -> {
                    if (linking == null) { throw new IllegalArgumentException("Invalid document id"); }
                    return linking;
                })
                .map(ExistingLinking::new)
                .map(linking -> {
                    linking.setLogo(LinkerUtils.getImagePath(configuration.rootPath(), linking.getPlatform()));
                    return linking;
                });
    }

}
