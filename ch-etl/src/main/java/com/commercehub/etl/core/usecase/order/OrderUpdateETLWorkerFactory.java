package com.commercehub.etl.core.usecase.order;

public interface OrderUpdateETLWorkerFactory {

    OrderUpdateETLWorker dispatch(String platform);

}
