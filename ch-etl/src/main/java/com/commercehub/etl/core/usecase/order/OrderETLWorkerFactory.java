package com.commercehub.etl.core.usecase.order;

public interface OrderETLWorkerFactory {

    OrderETLWorker dispatch(String platform);

}
