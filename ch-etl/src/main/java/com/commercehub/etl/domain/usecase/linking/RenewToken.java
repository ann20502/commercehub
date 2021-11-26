package com.commercehub.etl.domain.usecase.linking;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.service.RenewTokenResult;
import com.commercehub.etl.domain.service.RenewTokenService;
import com.commercehub.etl.domain.service.RenewTokenServiceProducer;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class RenewToken {

    @Inject
    RenewTokenServiceProducer producer;

    public Uni<RenewTokenResult> renew(Linking linking) {
        return Uni.createFrom().item(linking)
                .flatMap(input -> {
                    RenewTokenService renewTokenService = producer.produce(linking.getPlatform());
                    return renewTokenService.renew(input);
                });
    }

}
