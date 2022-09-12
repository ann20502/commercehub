package com.commercehub.etl.core.entitybuilder.product.shopee;

import com.commercehub.etl.core.entity.product.ItemShopee;

import java.time.Instant;
import java.util.List;

public class ItemShopeeBuilder {
    private long itemId;
    private long categoryId;
    private String name;
    private String description;
    private String sku;
    private Instant createTime;
    private Instant updateTime;
    private Instant extractTime;
    private List<ItemShopee.Attribute> attributes;
    private List<ItemShopee.Price> prices;
    private ItemShopee.Image image;
    private String weight;
    private ItemShopee.Dimension dimension;
    private ItemShopee.PreOrder preOrder;
    private List<ItemShopee.Wholesale> wholesales;
    private String condition;
    private String sizeChart;
    private String itemStatus;
    private boolean hasModel;
    private long promotionId;
    private List<ItemShopee.Video> videos;
    private ItemShopee.Brand brand;
    private long itemDangerous;
    private ItemShopee.StockSummary stockSummary;
    private ItemShopee.StockSeller stockSeller;
    private ItemShopee.StockShopee stockShopee;

    public ItemShopeeBuilder setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemShopeeBuilder setCategoryId(long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public ItemShopeeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemShopeeBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemShopeeBuilder setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public ItemShopeeBuilder setCreateTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public ItemShopeeBuilder setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public ItemShopeeBuilder setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
        return this;
    }

    public ItemShopeeBuilder setAttributes(List<ItemShopee.Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ItemShopeeBuilder setPrices(List<ItemShopee.Price> prices) {
        this.prices = prices;
        return this;
    }

    public ItemShopeeBuilder setImage(ItemShopee.Image image) {
        this.image = image;
        return this;
    }

    public ItemShopeeBuilder setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public ItemShopeeBuilder setDimension(ItemShopee.Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public ItemShopeeBuilder setPreOrder(ItemShopee.PreOrder preOrder) {
        this.preOrder = preOrder;
        return this;
    }

    public ItemShopeeBuilder setWholesales(List<ItemShopee.Wholesale> wholesales) {
        this.wholesales = wholesales;
        return this;
    }

    public ItemShopeeBuilder setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public ItemShopeeBuilder setSizeChart(String sizeChart) {
        this.sizeChart = sizeChart;
        return this;
    }

    public ItemShopeeBuilder setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
        return this;
    }

    public ItemShopeeBuilder setHasModel(boolean hasModel) {
        this.hasModel = hasModel;
        return this;
    }

    public ItemShopeeBuilder setPromotionId(long promotionId) {
        this.promotionId = promotionId;
        return this;
    }

    public ItemShopeeBuilder setVideos(List<ItemShopee.Video> videos) {
        this.videos = videos;
        return this;
    }

    public ItemShopeeBuilder setBrand(ItemShopee.Brand brand) {
        this.brand = brand;
        return this;
    }

    public ItemShopeeBuilder setItemDangerous(long itemDangerous) {
        this.itemDangerous = itemDangerous;
        return this;
    }

    public ItemShopeeBuilder setStockSummary(ItemShopee.StockSummary stockSummary) {
        this.stockSummary = stockSummary;
        return this;
    }

    public ItemShopeeBuilder setStockSeller(ItemShopee.StockSeller stockSeller) {
        this.stockSeller = stockSeller;
        return this;
    }

    public ItemShopeeBuilder setStockShopee(ItemShopee.StockShopee stockShopee) {
        this.stockShopee = stockShopee;
        return this;
    }

    public ItemShopee createItemShopee() {
        return new ItemShopee(itemId, categoryId, name, description, sku, createTime, updateTime, extractTime, attributes, prices, image, weight, dimension, preOrder, wholesales, condition, sizeChart, itemStatus, hasModel, promotionId, videos, brand, itemDangerous, stockSummary, stockSeller, stockShopee);
    }
}