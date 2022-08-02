package com.commercehub.etl.domain.usecase.product;

import io.smallrye.mutiny.Multi;

public interface BoostItem {

    Multi<Boolean> boost();

}
