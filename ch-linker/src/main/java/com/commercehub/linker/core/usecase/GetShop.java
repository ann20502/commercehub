package com.commercehub.linker.core.usecase;

import com.commercehub.linker.core.entity.Shop;
import io.smallrye.mutiny.Multi;

public interface GetShop {

    Multi<Shop> execute(String linkingStatus);

}
