package com.commercehub.linker.domain.usecase;

import com.commercehub.linker.domain.entity.Linking;
import com.commercehub.linker.domain.entity.Shop;
import com.commercehub.linker.domain.repository.LinkingRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GetShop {

    @Inject
    LinkingRepository repository;

    public Multi<Shop> getAll(String status) {
        return Multi.createFrom().iterable(repository.getAll(status))
                .collect()
                .asMap(Linking::getShopId, linking -> linking).toMulti() // Meant to get rid of duplicate, but it shouldn't duplicate with the introduction of document id
                .flatMap(map -> Multi.createFrom().iterable(map.entrySet()))
                .map(entry -> new Shop(
                        entry.getKey(),
                        entry.getValue().getShopName(),
                        entry.getValue().getShopRegion(),
                        entry.getValue().getPlatform()
                ));
    }

}
