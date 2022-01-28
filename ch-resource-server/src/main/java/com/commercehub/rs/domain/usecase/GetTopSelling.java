package com.commercehub.rs.domain.usecase;

import com.commercehub.rs.domain.entity.TopSelling;
import com.commercehub.rs.domain.repository.OrderRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDate;

@Dependent
public class GetTopSelling {

    @Inject
    OrderRepository repository;

    public Uni<TopSelling> get(String shopId, LocalDate from, LocalDate to, String zone) {
        return Uni.createFrom().item(repository.getTopSelling(shopId, from, to, zone));
    }

}
