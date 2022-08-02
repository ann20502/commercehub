package com.commercehub.etl.domain.repository;

import com.commercehub.etl.domain.entity.product.Boost;

import java.util.List;

public interface BoostRepository {

    Boost getSetting(String platform, String shopId);

    boolean saveSuccessAttempt(String platform, String shopId, List<Long> itemIds);

}
