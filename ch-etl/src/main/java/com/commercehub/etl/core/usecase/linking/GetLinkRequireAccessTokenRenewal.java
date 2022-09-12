package com.commercehub.etl.core.usecase.linking;

import com.commercehub.etl.core.entity.linking.Linking;
import io.smallrye.mutiny.Multi;

public interface GetLinkRequireAccessTokenRenewal {

    Multi<Linking> execute();

}
