package com.commercehub.etl.vm;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.linking.LinkingBuilder;
import com.commercehub.etl.domain.entity.linking.LinkingSimplified;
import com.commercehub.etl.domain.usecase.dataset.CreateDataset;
import com.commercehub.etl.domain.usecase.linking.GetLinking;
import com.commercehub.etl.domain.usecase.linking.HideSensitiveDetail;
import com.commercehub.etl.domain.usecase.linking.RecordSetupStatus;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class SetupViewModel {

    @Inject
    GetLinking getLinking;

    @Inject
    CreateDataset createDataset;

    @Inject
    RecordSetupStatus recordSetupStatus;

    @Inject
    HideSensitiveDetail hideSensitiveDetail;

    public Multi<LinkingSimplified> createDatasetIfNotExist() {
        return getLinking.getAll(Linking.STATUS_ACTIVE, false, true)
                .onItem()
                .transformToMulti(linking -> Multi.createFrom().iterable(linking))
                .filter(linking -> createDataset.create(linking))
                .filter(linking -> recordSetupStatus.record(linking.getId(), true))
                .map(linking -> new LinkingBuilder(linking).setSetup(true).createLinking())
                .map(linking -> hideSensitiveDetail.hide(linking));
    }

    public Multi<LinkingSimplified> updateExistingDataset() {
        return getLinking.getAll(Linking.STATUS_ACTIVE, true, true)
                .onItem()
                .transformToMulti(linking -> Multi.createFrom().iterable(linking))
                .map(linking -> {
                    linking.setPartnerSecret("");
                    return linking;
                })
                .filter(linking -> createDataset.create(linking))
                .map(linking -> hideSensitiveDetail.hide(linking));
    }

}
