package com.commercehub.etl.vm;

import com.commercehub.etl.domain.entity.Linking;
import com.commercehub.etl.domain.usecase.linking.GetLinking;
import com.commercehub.etl.domain.usecase.linking.MergeLinkingAndNewToken;
import com.commercehub.etl.domain.usecase.linking.RenewToken;
import com.commercehub.etl.domain.usecase.linking.UpdateLinking;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class LinkingViewModel {

    @Inject
    GetLinking getLinking;

    @Inject
    RenewToken renewToken;

    @Inject
    MergeLinkingAndNewToken mergeLinkingAndNewToken;

    @Inject
    UpdateLinking updateLinking;

    public Multi<Linking> renewTokenAndSave() {
        return getLinking
                .getAllWithTokenExpired(Linking.STATUS_ACTIVE)
                .flatMap(linking -> renewToken.renew(linking).toMulti()
                        .map(output -> mergeLinkingAndNewToken.merge(linking, output)))
                .filter(updateLinking::execute);
    }

}
