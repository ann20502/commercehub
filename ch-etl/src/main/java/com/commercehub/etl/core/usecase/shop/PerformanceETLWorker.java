package com.commercehub.etl.core.usecase.shop;

import com.commercehub.etl.core.entity.linking.Linking;
import io.smallrye.mutiny.Uni;

public interface PerformanceETLWorker {

    Uni<Boolean> extractTransformLoad(Linking linking);

}
