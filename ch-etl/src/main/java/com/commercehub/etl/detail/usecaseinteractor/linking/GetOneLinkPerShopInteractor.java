package com.commercehub.etl.detail.usecaseinteractor.linking;

import com.commercehub.etl.core.entity.ShopIdentifier;
import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.repository.LinkingRepository;
import com.commercehub.etl.core.usecase.linking.GetOneLinkPerShop;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;

@Dependent
public class GetOneLinkPerShopInteractor implements GetOneLinkPerShop {

    private final LinkingRepository linkingRepository;

    public GetOneLinkPerShopInteractor(LinkingRepository linkingRepository) {
        this.linkingRepository = linkingRepository;
    }

    @Override
    public Multi<Linking> execute() {
        return Multi.createFrom()
                .iterable(linkingRepository.getAll(Linking.STATUS_ACTIVE, true, true))
                .collect().asMap(linking -> new ShopIdentifier(linking.platform, linking.shopId))
                .toMulti()
                .flatMap(map -> Multi.createFrom().iterable(map.values()));
    }

}
