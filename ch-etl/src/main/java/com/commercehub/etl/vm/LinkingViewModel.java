package com.commercehub.etl.vm;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.linking.LinkingSimplified;
import com.commercehub.etl.domain.usecase.linking.*;
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

    @Inject
    HideSensitiveDetail hideSensitiveDetail;

    public Multi<LinkingSimplified> renewTokenAndSave() {
        return getLinking
                .getAllWithTokenExpired()
                .flatMap(linking -> renewToken.renew(linking).toMulti()
                        .map(output -> mergeLinkingAndNewToken.merge(linking, output)))
                .filter(updateLinking::execute)
                .map(linking -> hideSensitiveDetail.hide(linking));
    }

}
