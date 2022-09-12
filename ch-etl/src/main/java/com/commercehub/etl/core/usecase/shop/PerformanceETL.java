package com.commercehub.etl.core.usecase.shop;

import io.smallrye.mutiny.Multi;

public interface PerformanceETL {

    Multi<Boolean> extractTransformLoad();

}
