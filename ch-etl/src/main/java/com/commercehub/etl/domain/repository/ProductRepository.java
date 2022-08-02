package com.commercehub.etl.domain.repository;

import com.commercehub.etl.domain.entity.product.Item;

import java.util.List;

public interface ProductRepository {

    List<Item> getItemWithLatestUpdateTimestamp(String platform, String shopId, List<Long> itemIds);

    boolean saveItemUpdate(String platform, String shopId, List<Item> items);

}
