package com.commercehub.etl.detail.usecaseinteractor.linking;

import com.commercehub.etl.core.usecase.linking.AccessTokenRenewal;
import com.commercehub.etl.core.usecase.linking.AccessTokenRenewalWorker;
import com.commercehub.etl.core.usecase.linking.AccessTokenRenewalWorkerFactory;
import com.commercehub.etl.core.usecase.linking.GetLinkRequireAccessTokenRenewal;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class AccessTokenRenewalInteractor implements AccessTokenRenewal {

    @Inject
    GetLinkRequireAccessTokenRenewal getLinkRequireAccessTokenRenewal;

    @Inject
    AccessTokenRenewalWorkerFactory factory;

    @Override
    public Multi<Boolean> renew() {
        return getLinkRequireAccessTokenRenewal.execute()
                .flatMap(linking -> {
                    AccessTokenRenewalWorker worker = factory.dispatch(linking.platform);
                    return worker.renew(linking).toMulti();
                });
    }

}
