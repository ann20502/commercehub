package com.commercehub.etl.core.usecase.shop;

public interface PerformanceETLWorkerFactory {

    PerformanceETLWorker dispatch(String platform);

}
