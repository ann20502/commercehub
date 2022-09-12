package com.commercehub.etl.core.repository;

import com.commercehub.etl.core.entity.product.ItemShopee;

import java.util.List;

public interface ProductRepositoryShopee {

    List<ItemShopee> getItemWithLatestUpdateTimestamp(String platform, String shopId, List<Long> itemIds);

    boolean saveItemUpdate(String platform, String shopId, List<ItemShopee> itemShopees);

}
