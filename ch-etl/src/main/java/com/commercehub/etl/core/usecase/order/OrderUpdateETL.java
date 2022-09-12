package com.commercehub.etl.core.usecase.order;

import io.smallrye.mutiny.Uni;

public interface OrderUpdateETL {

    Uni<Boolean> extractTransformLoad(String documentId, String platform);

}
