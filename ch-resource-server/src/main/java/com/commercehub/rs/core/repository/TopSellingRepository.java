package com.commercehub.rs.core.repository;

import com.commercehub.rs.core.entity.TopSelling;
import com.commercehub.rs.core.entity.input.TopSellingInput;

public interface TopSellingRepository {

    TopSelling execute(TopSellingInput input, int limit);

}
