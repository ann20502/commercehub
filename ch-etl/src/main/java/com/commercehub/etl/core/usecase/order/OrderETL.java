package com.commercehub.etl.core.usecase.order;

import io.smallrye.mutiny.Uni;

public interface OrderETL {

    Uni<Boolean> extractTransformLoad(String documentId, String platform);

}
