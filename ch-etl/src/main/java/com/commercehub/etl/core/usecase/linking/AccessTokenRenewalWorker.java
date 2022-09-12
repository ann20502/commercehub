package com.commercehub.etl.core.usecase.linking;

import com.commercehub.etl.core.entity.linking.Linking;
import io.smallrye.mutiny.Uni;

public interface AccessTokenRenewalWorker {

    Uni<Boolean> renew(Linking linking);

}
