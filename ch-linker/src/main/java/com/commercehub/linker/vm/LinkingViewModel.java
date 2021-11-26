package com.commercehub.linker.vm;

import com.commercehub.link.client.repository.Linking;
import com.commercehub.linker.domain.entity.ExistingLinking;
import com.commercehub.linker.domain.entity.Shop;
import com.commercehub.linker.domain.usecase.GetExistingLinking;
import com.commercehub.linker.domain.usecase.GetShop;
import com.commercehub.linker.domain.usecase.SetupLinking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Map;

@Dependent
public class LinkingViewModel {

    @Inject
    GetExistingLinking getExistingLinking;

    @Inject
    GetShop getShop;

    @Inject
    SetupLinking setupLinking;

    public Multi<ExistingLinking> getExistingLinking() {
        return getExistingLinking.getAll(true);
    }

    public Uni<ExistingLinking> getExistingLinking(String documentId) {
        return getExistingLinking.getById(documentId);
    }

    public Multi<Shop> getShop() {
        return getShop.getAll(Linking.STATUS_ACTIVE);
    }

    public Uni<Boolean> setupLinking(String documentId, Map<String,Object> values) {
        return setupLinking.execute(documentId, values);
    }

}
