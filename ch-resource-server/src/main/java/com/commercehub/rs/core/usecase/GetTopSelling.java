package com.commercehub.rs.core.usecase;

import com.commercehub.rs.core.entity.TopSelling;
import com.commercehub.rs.core.entity.input.TopSellingInput;
import io.smallrye.mutiny.Uni;

public interface GetTopSelling {

    Uni<TopSelling> execute(TopSellingInput input, int noOfItem);

}
