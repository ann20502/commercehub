package com.commercehub.linker.detail.usecase;

import com.commercehub.core.entity.ShopIdentifier;
import com.commercehub.linker.core.entity.Linking;
import com.commercehub.linker.core.entity.Shop;
import com.commercehub.linker.core.entitybuilder.ShopBuilder;
import com.commercehub.linker.core.repository.LinkingRepository;
import com.commercehub.linker.core.usecase.GetShop;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;

@Dependent
public class GetShopInteractor implements GetShop {

    private final LinkingRepository repository;

    public GetShopInteractor(LinkingRepository repository) {
        this.repository = repository;
    }

    // Get one link per shop
    @Override
    public Multi<Shop> execute(String linkingStatus) {
        return Multi.createFrom().iterable(repository.getAll(Linking.STATUS_ACTIVE))
                .collect().asMap(linking -> new ShopIdentifier(linking.platform, linking.shopId))
                .toMulti()
                .flatMap(map -> Multi.createFrom().iterable(map.values()))
                .map(this::toShop);
    }

    private Shop toShop(Linking linking) {
        return new ShopBuilder()
                .setPlatform(linking.platform)
                .setShopId(linking.shopId)
                .setShopName(linking.shopName)
                .setShopStatus(linking.shopStatus)
                .setShopRegion(linking.shopRegion)
                .createShop();
    }

}
