package com.commercehub.etl.detail.repository.product.builder;

import com.commercehub.etl.detail.repository.product.BQItemShopee;

import java.time.Instant;
import java.util.List;

public class BQItemShopeeBuilder {
    private long itemId;
    private long categoryId;
    private String name;
    private String description;
    private String sku;
    private Instant createTime;
    private Instant updateTime;
    private Instant extractTime;
    private List<BQItemShopee.Attribute> attributes;
    private List<BQItemShopee.Price> prices;
    private BQItemShopee.Image image;
    private String weight;
    private BQItemShopee.Dimension dimension;
    private BQItemShopee.PreOrder preOrder;
    private List<BQItemShopee.Wholesale> wholesales;
    private String condition;
    private String sizeChart;
    private String itemStatus;
    private boolean hasModel;
    private long promotionId;
    private List<BQItemShopee.Video> videos;
    private BQItemShopee.Brand brand;
    private long itemDangerous;
    private BQItemShopee.StockSummary stockSummary;
    private BQItemShopee.StockSeller stockSeller;
    private BQItemShopee.StockShopee stockShopee;

    public BQItemShopeeBuilder setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BQItemShopeeBuilder setCategoryId(long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public BQItemShopeeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BQItemShopeeBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public BQItemShopeeBuilder setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public BQItemShopeeBuilder setCreateTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public BQItemShopeeBuilder setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public BQItemShopeeBuilder setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
        return this;
    }

    public BQItemShopeeBuilder setAttributes(List<BQItemShopee.Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public BQItemShopeeBuilder setPrices(List<BQItemShopee.Price> prices) {
        this.prices = prices;
        return this;
    }

    public BQItemShopeeBuilder setImage(BQItemShopee.Image image) {
        this.image = image;
        return this;
    }

    public BQItemShopeeBuilder setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public BQItemShopeeBuilder setDimension(BQItemShopee.Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public BQItemShopeeBuilder setPreOrder(BQItemShopee.PreOrder preOrder) {
        this.preOrder = preOrder;
        return this;
    }

    public BQItemShopeeBuilder setWholesales(List<BQItemShopee.Wholesale> wholesales) {
        this.wholesales = wholesales;
        return this;
    }

    public BQItemShopeeBuilder setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public BQItemShopeeBuilder setSizeChart(String sizeChart) {
        this.sizeChart = sizeChart;
        return this;
    }

    public BQItemShopeeBuilder setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
        return this;
    }

    public BQItemShopeeBuilder setHasModel(boolean hasModel) {
        this.hasModel = hasModel;
        return this;
    }

    public BQItemShopeeBuilder setPromotionId(long promotionId) {
        this.promotionId = promotionId;
        return this;
    }

    public BQItemShopeeBuilder setVideos(List<BQItemShopee.Video> videos) {
        this.videos = videos;
        return this;
    }

    public BQItemShopeeBuilder setBrand(BQItemShopee.Brand brand) {
        this.brand = brand;
        return this;
    }

    public BQItemShopeeBuilder setItemDangerous(long itemDangerous) {
        this.itemDangerous = itemDangerous;
        return this;
    }

    public BQItemShopeeBuilder setStockSummary(BQItemShopee.StockSummary stockSummary) {
        this.stockSummary = stockSummary;
        return this;
    }

    public BQItemShopeeBuilder setStockSeller(BQItemShopee.StockSeller stockSeller) {
        this.stockSeller = stockSeller;
        return this;
    }

    public BQItemShopeeBuilder setStockShopee(BQItemShopee.StockShopee stockShopee) {
        this.stockShopee = stockShopee;
        return this;
    }

    public BQItemShopee createBQItemShopee() {
        return new BQItemShopee(itemId, categoryId, name, description, sku, createTime, updateTime, extractTime, attributes, prices, image, weight, dimension, preOrder, wholesales, condition, sizeChart, itemStatus, hasModel, promotionId, videos, brand, itemDangerous, stockSummary, stockSeller, stockShopee);
    }
}