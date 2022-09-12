package com.commercehub.etl.detail.usecaseinteractor.product;

import com.commercehub.etl.core.usecase.linking.GetOneLinkPerShop;
import com.commercehub.etl.core.usecase.product.ItemETL;
import com.commercehub.etl.core.usecase.product.ItemETLWorker;
import com.commercehub.etl.core.usecase.product.ItemETLWorkerFactory;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;

@Dependent
public class ItemETLInteractor implements ItemETL {

    private final GetOneLinkPerShop getOneLinkPerShop;
    private final ItemETLWorkerFactory workerFactory;

    public ItemETLInteractor(GetOneLinkPerShop getOneLinkPerShop, ItemETLWorkerFactory workerFactory) {
        this.getOneLinkPerShop = getOneLinkPerShop;
        this.workerFactory = workerFactory;
    }

    @Override
    public Multi<Boolean> extractTransformLoad() {
        return getOneLinkPerShop.execute()
                .flatMap(linking -> {
                    ItemETLWorker worker = workerFactory.dispatch(linking.platform);
                    return worker.extractTransformLoad(linking).toMulti();
                });
    }

}
