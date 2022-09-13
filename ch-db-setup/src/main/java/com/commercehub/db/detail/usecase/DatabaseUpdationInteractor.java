package com.commercehub.db.detail.usecase;

import com.commercehub.db.core.usecase.CreateOrUpdateDatabase;
import com.commercehub.db.core.usecase.DatabaseUpdation;
import com.commercehub.db.core.usecase.GetOneLinkPerShop;
import com.google.cloud.Tuple;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class DatabaseUpdationInteractor implements DatabaseUpdation {

    @Inject
    GetOneLinkPerShop getOneLinkPerShop;

    @Inject
    CreateOrUpdateDatabase createOrUpdateDatabase;

    @Override
    public Multi<Boolean> update() {
        return getOneLinkPerShop.execute(true)
                .map(linking -> Tuple.of(linking, createOrUpdateDatabase.execute(linking)))
                .map(Tuple::y);
    }

}
