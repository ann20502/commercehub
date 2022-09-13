package com.commercehub.etl.detail.usecase.linking;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.repository.LinkingRepository;
import com.commercehub.etl.core.usecase.linking.GetLinkRequireAccessTokenRenewal;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;

@Dependent
public class GetLinkRequireAccessTokenRenewalInteractor implements GetLinkRequireAccessTokenRenewal {

    private final LinkingRepository repository;

    public GetLinkRequireAccessTokenRenewalInteractor(LinkingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Multi<Linking> execute() {
        final int FORWARD_THIRTY_MINUTES = 30;
        return Multi.createFrom().iterable(
                repository.getAllWithTokenExpired(
                        Linking.STATUS_ACTIVE,
                        FORWARD_THIRTY_MINUTES
                )
        );
    }

}
