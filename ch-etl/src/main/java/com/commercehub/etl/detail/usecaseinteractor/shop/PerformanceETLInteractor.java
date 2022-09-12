package com.commercehub.etl.detail.usecaseinteractor.shop;

import com.commercehub.etl.core.usecase.linking.GetOneLinkPerShop;
import com.commercehub.etl.core.usecase.shop.PerformanceETL;
import com.commercehub.etl.core.usecase.shop.PerformanceETLWorkerFactory;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;

@Dependent
public class PerformanceETLInteractor implements PerformanceETL {

    private final GetOneLinkPerShop getOneLinkPerShop;
    private final PerformanceETLWorkerFactory workerFactory;

    public PerformanceETLInteractor(GetOneLinkPerShop getOneLinkPerShop, PerformanceETLWorkerFactory workerFactory) {
        this.getOneLinkPerShop = getOneLinkPerShop;
        this.workerFactory = workerFactory;
    }

    @Override
    public Multi<Boolean> extractTransformLoad() {
        return getOneLinkPerShop.execute()
                .flatMap(linking -> workerFactory
                        .dispatch(linking.platform)
                        .extractTransformLoad(linking)
                        .toMulti()
                );
    }

}
