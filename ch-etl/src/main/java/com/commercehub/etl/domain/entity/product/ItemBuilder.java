package com.commercehub.etl.domain.entity.product;

import java.time.Instant;
import java.util.List;

public class ItemBuilder {
    private long itemId;
    private long categoryId;
    private String name;
    private String description;
    private String sku;
    private Instant createTime;
    private Instant updateTime;
    private Instant extractTime;
    private List<Item.Attribute> attributes;
    private List<Item.Price> prices;
    private Item.Image image;
    private String weight;
    private Item.Dimension dimension;
    private Item.PreOrder preOrder;
    private List<Item.Wholesale> wholesales;
    private String condition;
    private String sizeChart;
    private String itemStatus;
    private boolean hasModel;
    private long promotionId;
    private List<Item.Video> videos;
    private Item.Brand brand;
    private long itemDangerous;
    private Item.StockSummary stockSummary;
    private Item.StockSeller stockSeller;
    private Item.StockShopee stockShopee;

    public ItemBuilder setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemBuilder setCategoryId(long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemBuilder setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public ItemBuilder setCreateTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public ItemBuilder setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public ItemBuilder setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
        return this;
    }

    public ItemBuilder setAttributes(List<Item.Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ItemBuilder setPrices(List<Item.Price> prices) {
        this.prices = prices;
        return this;
    }

    public ItemBuilder setImage(Item.Image image) {
        this.image = image;
        return this;
    }

    public ItemBuilder setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public ItemBuilder setDimension(Item.Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public ItemBuilder setPreOrder(Item.PreOrder preOrder) {
        this.preOrder = preOrder;
        return this;
    }

    public ItemBuilder setWholesales(List<Item.Wholesale> wholesales) {
        this.wholesales = wholesales;
        return this;
    }

    public ItemBuilder setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public ItemBuilder setSizeChart(String sizeChart) {
        this.sizeChart = sizeChart;
        return this;
    }

    public ItemBuilder setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
        return this;
    }

    public ItemBuilder setHasModel(boolean hasModel) {
        this.hasModel = hasModel;
        return this;
    }

    public ItemBuilder setPromotionId(long promotionId) {
        this.promotionId = promotionId;
        return this;
    }

    public ItemBuilder setVideos(List<Item.Video> videos) {
        this.videos = videos;
        return this;
    }

    public ItemBuilder setBrand(Item.Brand brand) {
        this.brand = brand;
        return this;
    }

    public ItemBuilder setItemDangerous(long itemDangerous) {
        this.itemDangerous = itemDangerous;
        return this;
    }

    public ItemBuilder setStockSummary(Item.StockSummary stockSummary) {
        this.stockSummary = stockSummary;
        return this;
    }

    public ItemBuilder setStockSeller(Item.StockSeller stockSeller) {
        this.stockSeller = stockSeller;
        return this;
    }

    public ItemBuilder setStockShopee(Item.StockShopee stockShopee) {
        this.stockShopee = stockShopee;
        return this;
    }

    public Item createItem() {
        return new Item(itemId, categoryId, name, description, sku, createTime, updateTime, extractTime, attributes, prices, image, weight, dimension, preOrder, wholesales, condition, sizeChart, itemStatus, hasModel, promotionId, videos, brand, itemDangerous, stockSummary, stockSeller, stockShopee);
    }
}