package com.commercehub.etl.domain.repository;

import com.commercehub.etl.domain.entity.shop.Performance;
import io.smallrye.mutiny.Uni;

public interface ShopRepository {

    Uni<Boolean> savePerformance(String platform, String shopId, Performance performance);

}
