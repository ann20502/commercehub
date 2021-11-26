package com.commercehub.etl.domain.service;

import com.commercehub.etl.domain.entity.linking.Linking;
import io.smallrye.mutiny.Uni;

public interface RenewTokenService {

    Uni<RenewTokenResult> renew(Linking linking);

}
