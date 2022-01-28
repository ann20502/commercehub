package com.commercehub.rs.domain.usecase;

import com.commercehub.rs.domain.entity.ShopPerformance;
import com.commercehub.rs.domain.repository.ShopRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GetShopPerformance {

    @Inject
    ShopRepository shopRepository;

    public Uni<ShopPerformance> get(String dataset) {
        return Uni.createFrom().item(shopRepository.getShopPerformance(dataset));
    }

}
