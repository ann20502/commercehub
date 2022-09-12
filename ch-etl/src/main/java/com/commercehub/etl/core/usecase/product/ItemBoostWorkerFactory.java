package com.commercehub.etl.core.usecase.product;

public interface ItemBoostWorkerFactory {

    ItemBoostWorker dispatch(String platform);

}
