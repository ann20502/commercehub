package com.commercehub.etl.domain.service;

import com.commercehub.etl.qualifier.PlatformLiteral;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Dependent
public class RenewTokenServiceProducer {

    @Inject
    @Any
    Instance<RenewTokenService> renewTokenServices;

    public RenewTokenService produce(String platform) {
        return renewTokenServices.select(new PlatformLiteral(platform)).get();
    }

}
