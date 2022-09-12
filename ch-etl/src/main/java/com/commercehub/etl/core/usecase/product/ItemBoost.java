package com.commercehub.etl.core.usecase.product;

import io.smallrye.mutiny.Multi;

public interface ItemBoost {

    Multi<Boolean> boost();

}
