package com.commercehub.etl.core.usecase.product;

public interface ItemETLWorkerFactory {

    ItemETLWorker dispatch(String platform);

}
