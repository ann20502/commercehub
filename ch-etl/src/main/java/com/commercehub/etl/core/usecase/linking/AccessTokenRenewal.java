package com.commercehub.etl.core.usecase.linking;

import io.smallrye.mutiny.Multi;

public interface AccessTokenRenewal {

    Multi<Boolean> renew();

}
