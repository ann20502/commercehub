package com.commercehub.db.detail.usecase;

import com.commercehub.core.entity.ShopIdentifier;
import com.commercehub.db.core.entity.Linking;
import com.commercehub.db.core.repository.LinkingRepository;
import com.commercehub.db.core.usecase.GetOneLinkPerShop;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;

@Dependent
public class GetOneLinkPerShopInteractor implements GetOneLinkPerShop {

    private final LinkingRepository linkingRepository;

    public GetOneLinkPerShopInteractor(LinkingRepository linkingRepository) {
        this.linkingRepository = linkingRepository;
    }

    @Override
    public Multi<Linking> execute(boolean hasSetup) {
        return Multi.createFrom()
                .iterable(linkingRepository.getAll(Linking.STATUS_ACTIVE, hasSetup, true))
                .collect().asMap(linking -> new ShopIdentifier(linking.platform, linking.shopId))
                .toMulti()
                .flatMap(map -> Multi.createFrom().iterable(map.values()));
    }

}
