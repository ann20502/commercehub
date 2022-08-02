package com.commercehub.etl.domain.entity.product;

import com.squareup.moshi.Json;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class Item {

    @Json(name = "item_id")
    public final long itemId;

    @Json(name = "category_id")
    public final long categoryId;

    @Json(name = "name")
    public final String name;

    @Json(name = "description")
    public final String description;

    @Json(name = "sku")
    public final String sku;

    @Json(name = "create_time")
    public final Instant createTime;

    @Json(name = "update_time")
    public final Instant updateTime;

    @Json(name = "extract_time")
    public final Instant extractTime;

    @Json(name = "attributes")
    public final List<Attribute> attributes;

    @Json(name = "prices")
    public final List<Price> prices;

    @Json(name = "image")
    public final Image image;

    @Json(name = "weight")
    public final String weight;

    @Json(name = "dimension")
    public final Dimension dimension;

    @Json(name = "pre_order")
    public final PreOrder preOrder;

    @Json(name = "wholesales")
    public final List<Wholesale> wholesales;

    @Json(name = "condition")
    public final String condition;

    @Json(name = "size_chart")
    public final String sizeChart;

    @Json(name = "item_status")
    public final String itemStatus;

    @Json(name = "has_model")
    public final boolean hasModel;

    @Json(name = "promotion_id")
    public final long promotionId;

    @Json(name = "videos")
    public final List<Video> videos;

    @Json(name = "brand")
    public final Brand brand;

    @Json(name = "item_dangerous")
    public final long itemDangerous;

    @Json(name = "stock_summary")
    public final StockSummary stockSummary;

    @Json(name = "stock_seller")
    public final StockSeller stockSeller;

    @Json(name = "stock_shopee")
    public final StockShopee stockShopee;

    public Item(long itemId, long categoryId, String name, String description, String sku, Instant createTime, Instant updateTime, Instant extractTime, List<Attribute> attributes, List<Price> prices, Image image, String weight, Dimension dimension, PreOrder preOrder, List<Wholesale> wholesales, String condition, String sizeChart, String itemStatus, boolean hasModel, long promotionId, List<Video> videos, Brand brand, long itemDangerous, StockSummary stockSummary, StockSeller stockSeller, StockShopee stockShopee) {
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

        @Json(name = "attribute_id")
        public final long attributeId;

        @Json(name = "original_attribute_name")
        public final String originalAttributeName;

        @Json(name = "is_mandatory")
        public final boolean is_mandatory;

        @Json(name = "attribute_values")
        public List<AttributeValue> attributeValues;

        public Attribute(long attributeId, String originalAttributeName, boolean is_mandatory, List<AttributeValue> attributeValues) {
            this.attributeId = attributeId;
            this.originalAttributeName = originalAttributeName;
            this.is_mandatory = is_mandatory;
            this.attributeValues = attributeValues;
        }
    }

    public static class AttributeValue {

        @Json(name = "attribute_value_id")
        public final long attributeValueId;

        @Json(name = "original_value_name")
        public final String originalValueName;

        @Json(name = "value_unit")
        public final String valueUnit;

        public AttributeValue(long attributeValueId, String originalValueName, String valueUnit) {
            this.attributeValueId = attributeValueId;
            this.originalValueName = originalValueName;
            this.valueUnit = valueUnit;
        }
    }

    public static class Price {

        @Json(name = "currency")
        public final String currency;

        @Json(name = "original_price")
        public final BigDecimal originalPrice;

        @Json(name = "current_price")
        public final BigDecimal currentPrice;

        @Json(name = "inflated_price_of_current_price")
        public final BigDecimal inflatedPriceOfCurrentPrice;

        @Json(name = "sip_item_price")
        public final BigDecimal sipItemPrice;

        @Json(name = "sip_item_price_source")
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

        @Json(name = "image_urls")
        public final String[] imageUrls;

        @Json(name = "image_ids")
        public final String[] imageIds;

        public Image(String[] imageUrls, String[] imageIds) {
            this.imageUrls = imageUrls;
            this.imageIds = imageIds;
        }
    }

    public static class Dimension {

        @Json(name = "length")
        public final long length;

        @Json(name = "width")
        public final long width;

        @Json(name = "height")
        public final long height;

        public Dimension(long length, long width, long height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }
    }

    public static class PreOrder {

        @Json(name = "is_pre_order")
        public final boolean isPreOrder;

        @Json(name = "days_to_ship")
        public final long days_to_ship;

        public PreOrder(boolean isPreOrder, long days_to_ship) {
            this.isPreOrder = isPreOrder;
            this.days_to_ship = days_to_ship;
        }
    }

    public static class Wholesale {

        @Json(name = "min_count")
        public final long min_count;

        @Json(name = "max_count")
        public final long max_count;

        @Json(name = "unit_price")
        public final BigDecimal unitPrice;

        @Json(name = "inflated_price_of_unit_price")
        public final BigDecimal inflatedPriceOfUnitPrice;

        public Wholesale(long min_count, long max_count, BigDecimal unitPrice, BigDecimal inflatedPriceOfUnitPrice) {
            this.min_count = min_count;
            this.max_count = max_count;
            this.unitPrice = unitPrice;
            this.inflatedPriceOfUnitPrice = inflatedPriceOfUnitPrice;
        }
    }

    public static class Video {

        @Json(name = "video_url")
        public final String videoUrl;

        @Json(name = "thumbnail_url")
        public final String thumbnailUrl;

        @Json(name = "duration")
        public final long duration;

        public Video(String videoUrl, String thumbnailUrl, long duration) {
            this.videoUrl = videoUrl;
            this.thumbnailUrl = thumbnailUrl;
            this.duration = duration;
        }
    }

    public static class Brand {

        @Json(name = "brand_id")
        public final long brandId;

        @Json(name = "original_brand_name")
        public final String originalBrandName;

        public Brand(long brandId, String originalBrandName) {
            this.brandId = brandId;
            this.originalBrandName = originalBrandName;
        }
    }

    public static class StockSummary {

        @Json(name = "total_reserved_stock")
        public final long totalReservedStock;

        @Json(name = "total_available_stock")
        public final long totalAvailableStock;

        public StockSummary(long totalReservedStock, long totalAvailableStock) {
            this.totalReservedStock = totalReservedStock;
            this.totalAvailableStock = totalAvailableStock;
        }
    }

    public static class StockSeller {

        @Json(name = "location_id")
        public final String locationId;

        @Json(name = "stock")
        public final long stock;

        public StockSeller(String locationId, long stock) {
            this.locationId = locationId;
            this.stock = stock;
        }
    }

    public static class StockShopee {

        @Json(name = "location_id")
        public final String locationId;

        @Json(name = "stock")
        public final long stock;

        public StockShopee(String locationId, long stock) {
            this.locationId = locationId;
            this.stock = stock;
        }
    }

}
