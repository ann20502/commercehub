package com.commercehub.rs.core.entitybuilder;

import com.commercehub.rs.core.entity.TopSelling;

public class TopSellingItemBuilder {
    private long itemId;
    private String itemName;
    private String modelName;
    private String imageUrl;
    private long sold;

    public TopSellingItemBuilder setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public TopSellingItemBuilder setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public TopSellingItemBuilder setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public TopSellingItemBuilder setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public TopSellingItemBuilder setSold(long sold) {
        this.sold = sold;
        return this;
    }

    public TopSelling.Item createItem() {
        return new TopSelling.Item(itemId, itemName, modelName, imageUrl, sold);
    }
}