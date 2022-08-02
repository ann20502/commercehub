package com.commercehub.etl.domain.usecase.product;

import io.smallrye.mutiny.Uni;

public interface ExtractItemUpdate {

    Uni<Boolean> extract();

}
