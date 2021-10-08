package com.commercehub.etl.domain.service;

import com.commercehub.etl.domain.entity.Linking;
import com.commercehub.etl.domain.entity.RenewTokenResult;
import io.smallrye.mutiny.Uni;

public interface RenewTokenService {

    Uni<RenewTokenResult> renew(Linking linking);

}
