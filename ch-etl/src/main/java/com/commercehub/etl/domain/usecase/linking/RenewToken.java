package com.commercehub.etl.domain.usecase.linking;

import com.commercehub.etl.domain.entity.Linking;
import com.commercehub.etl.domain.entity.RenewTokenResult;
import com.commercehub.etl.domain.service.RenewTokenService;
import com.commercehub.etl.qualifier.PlatformLiteral;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Dependent
public class RenewToken {

    @Inject
    @Any
    Instance<RenewTokenService> renewTokenServices;

    public Uni<RenewTokenResult> renew(Linking linking) {
        RenewTokenService renewTokenService = getRenewTokenService(linking.getPlatform());
        return renewTokenService.renew(linking);
    }

    private RenewTokenService getRenewTokenService(String platform) {
        return renewTokenServices.select(new PlatformLiteral(platform)).get();
    }

}
