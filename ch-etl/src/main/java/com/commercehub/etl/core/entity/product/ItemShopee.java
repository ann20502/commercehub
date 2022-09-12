package com.commercehub.etl.core.entity.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class ItemShopee implements Item {

    public final long itemId;

    public final long categoryId;

    public final String name;

    public final String description;

    public final String sku;

    public final Instant createTime;

    public final Instant updateTime;

    public final Instant extractTime;

    public final List<Attribute> attributes;

    public final List<Price> prices;

    public final Image image;

    public final String weight;

    public final Dimension dimension;

    public final PreOrder preOrder;

    public final List<Wholesale> wholesales;

    public final String condition;

    public final String sizeChart;

    public final String itemStatus;

    public final boolean hasModel;

    public final long promotionId;

    public final List<Video> videos;

    public final Brand brand;

    public final long itemDangerous;

    public final StockSummary stockSummary;

    public final StockSeller stockSeller;

    public final StockShopee stockShopee;

    public ItemShopee(long itemId, long categoryId, String name, String description, String sku, Instant createTime, Instant updateTime, Instant extractTime, List<Attribute> attributes, List<Price> prices, Image image, String weight, Dimension dimension, PreOrder preOrder, List<Wholesale> wholesales, String condition, String sizeChart, String itemStatus, boolean hasModel, long promotionId, List<Video> videos, Brand brand, long itemDangerous, StockSummary stockSummary, StockSeller stockSeller, StockShopee stockShopee) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.extractTime = extractTime;
        this.attributes = attributes;
        this.prices = prices;
        this.image = image;
        this.weight = weight;
        this.dimension = dimension;
        this.preOrder = preOrder;
        this.wholesales = wholesales;
        this.condition = condition;
        this.sizeChart = sizeChart;
        this.itemStatus = itemStatus;
        this.hasModel = hasModel;
        this.promotionId = promotionId;
        this.videos = videos;
        this.brand = brand;
        this.itemDangerous = itemDangerous;
        this.stockSummary = stockSummary;
        this.stockSeller = stockSeller;
        this.stockShopee = stockShopee;
    }

    public static class Attribute {

        public final long attributeId;

        public final String originalAttributeName;

        public final boolean is_mandatory;

        public List<ItemShopee.AttributeValue> attributeValues;

        public Attribute(long attributeId, String originalAttributeName, boolean is_mandatory, List<AttributeValue> attributeValues) {
            this.attributeId = attributeId;
            this.originalAttributeName = originalAttributeName;
            this.is_mandatory = is_mandatory;
            this.attributeValues = attributeValues;
        }
    }

    public static class AttributeValue {

        public final long attributeValueId;

        public final String originalValueName;

        public final String valueUnit;

        public AttributeValue(long attributeValueId, String originalValueName, String valueUnit) {
            this.attributeValueId = attributeValueId;
            this.originalValueName = originalValueName;
            this.valueUnit = valueUnit;
        }
    }

    public static class Price {

        public final String currency;

        public final BigDecimal originalPrice;

        public final BigDecimal currentPrice;

        public final BigDecimal inflatedPriceOfCurrentPrice;

        public final BigDecimal sipItemPrice;

        public final String sipItemPriceSource;

        public Price(String currency, BigDecimal originalPrice, BigDecimal currentPrice, BigDecimal inflatedPriceOfCurrentPrice, BigDecimal sipItemPrice, String sipItemPriceSource) {
            this.currency = currency;
            this.originalPrice = originalPrice;
            this.currentPrice = currentPrice;
            this.inflatedPriceOfCurrentPrice = inflatedPriceOfCurrentPrice;
            this.sipItemPrice = sipItemPrice;
            this.sipItemPriceSource = sipItemPriceSource;
        }
    }

    public static class Image {

        public final String[] imageUrls;

        public final String[] imageIds;

        public Image(String[] imageUrls, String[] imageIds) {
            this.imageUrls = imageUrls;
            this.imageIds = imageIds;
        }
    }

    public static class Dimension {

        public final long length;

        public final long width;

        public final long height;

        public Dimension(long length, long width, long height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }
    }

    public static class PreOrder {

        public final boolean isPreOrder;

        public final long days_to_ship;

        public PreOrder(boolean isPreOrder, long days_to_ship) {
            this.isPreOrder = isPreOrder;
            this.days_to_ship = days_to_ship;
        }
    }

    public static class Wholesale {

        public final long min_count;

        public final long max_count;

        public final BigDecimal unitPrice;

        public final BigDecimal inflatedPriceOfUnitPrice;

        public Wholesale(long min_count, long max_count, BigDecimal unitPrice, BigDecimal inflatedPriceOfUnitPrice) {
            this.min_count = min_count;
            this.max_count = max_count;
            this.unitPrice = unitPrice;
            this.inflatedPriceOfUnitPrice = inflatedPriceOfUnitPrice;
        }
    }

    public static class Video {

        public final String videoUrl;

        public final String thumbnailUrl;

        public final long duration;

        public Video(String videoUrl, String thumbnailUrl, long duration) {
            this.videoUrl = videoUrl;
            this.thumbnailUrl = thumbnailUrl;
            this.duration = duration;
        }
    }

    public static class Brand {

        public final long brandId;

        public final String originalBrandName;

        public Brand(long brandId, String originalBrandName) {
            this.brandId = brandId;
            this.originalBrandName = originalBrandName;
        }
    }

    public static class StockSummary {

        public final long totalReservedStock;

        public final long totalAvailableStock;

        public StockSummary(long totalReservedStock, long totalAvailableStock) {
            this.totalReservedStock = totalReservedStock;
            this.totalAvailableStock = totalAvailableStock;
        }
    }

    public static class StockSeller {

        public final String locationId;

        public final long stock;

        public StockSeller(String locationId, long stock) {
            this.locationId = locationId;
            this.stock = stock;
        }
    }

    public static class StockShopee {

        public final String locationId;

        public final long stock;

        public StockShopee(String locationId, long stock) {
            this.locationId = locationId;
            this.stock = stock;
        }
    }

}
