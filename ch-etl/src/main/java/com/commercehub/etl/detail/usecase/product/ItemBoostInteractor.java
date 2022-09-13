package com.commercehub.etl.detail.usecase.product;

import com.commercehub.etl.core.usecase.linking.GetOneLinkPerShop;
import com.commercehub.etl.core.usecase.product.ItemBoost;
import com.commercehub.etl.core.usecase.product.ItemBoostWorker;
import com.commercehub.etl.core.usecase.product.ItemBoostWorkerFactory;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ItemBoostInteractor implements ItemBoost {

    @Inject
    GetOneLinkPerShop getOneLinkPerShop;

    @Inject
    ItemBoostWorkerFactory factory;

    @Override
    public Multi<Boolean> boost() {
        return getOneLinkPerShop.execute()
                .flatMap(linking -> {
                    ItemBoostWorker worker = factory.dispatch(linking.platform);
                    return worker.boost(linking).toMulti();
                });
    }

}
