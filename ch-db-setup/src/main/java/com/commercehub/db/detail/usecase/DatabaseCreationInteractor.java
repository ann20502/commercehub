package com.commercehub.db.detail.usecase;

import com.commercehub.db.core.entity.Linking;
import com.commercehub.db.core.repository.LinkingRepository;
import com.commercehub.db.core.usecase.CreateOrUpdateDatabase;
import com.commercehub.db.core.usecase.DatabaseCreation;
import com.commercehub.db.core.usecase.GetOneLinkPerShop;
import com.google.cloud.Tuple;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class DatabaseCreationInteractor implements DatabaseCreation {

    @Inject
    GetOneLinkPerShop getOneLinkPerShop;

    @Inject
    CreateOrUpdateDatabase createOrUpdateDatabase;

    @Inject
    LinkingRepository repository;

    @Override
    public Multi<Boolean> create() {
        return getOneLinkPerShop.execute(false)
                .map(linking -> Tuple.of(linking, createOrUpdateDatabase.execute(linking)))
                .filter(linkingAndResult -> {
                    boolean result = linkingAndResult.y();
                    if (!result) { return false; }
                    Linking linking = linkingAndResult.x();
                    return repository.recordSetup(linking.id, result);
                })
                .map(Tuple::y);
    }

}
