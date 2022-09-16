package com.commercehub.rs.core.usecase;

import com.commercehub.rs.core.entity.TopSelling;
import com.commercehub.rs.core.entity.input.TopSellingInput;

public interface TopSellingCalculator {

    TopSelling calculate(TopSellingInput input, int noOfItem);

}
