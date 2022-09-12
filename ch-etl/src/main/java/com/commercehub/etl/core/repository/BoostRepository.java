package com.commercehub.etl.core.repository;

import com.commercehub.etl.core.entity.boost.Boost;

import java.util.List;

public interface BoostRepository {

    List<Boost> getFirst(String platform, String shopId);

    boolean saveSuccessAttempt(String platform, String shopId, List<Long> itemIds);

}
