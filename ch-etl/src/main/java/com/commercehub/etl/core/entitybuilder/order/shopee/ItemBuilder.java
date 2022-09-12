package com.commercehub.etl.core.entitybuilder.order.shopee;

import com.commercehub.etl.core.entity.order.OrderShopee;

import java.math.BigDecimal;

public class ItemBuilder {
    private long id;
    private String name;
    private String sku;
    private long modelId;
    private String modelName;
    private String modelSku;
    private int modelQuantityPurchased;
    private BigDecimal modelOriginalPrice;
    private BigDecimal modelDiscountedPrice;
    private boolean wholesale;
    private BigDecimal weight;
    private boolean addOnDeal;
    private boolean mainItem;
    private long addOnDealId;
    private String promotionType;
    private long promotionId;
    private long orderItemId;
    private long promotionGroupId;
    private OrderShopee.ImageInfo imageInfo;

    public ItemBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public ItemBuilder setModelId(long modelId) {
        this.modelId = modelId;
        return this;
    }

    public ItemBuilder setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public ItemBuilder setModelSku(String modelSku) {
        this.modelSku = modelSku;
        return this;
    }

    public ItemBuilder setModelQuantityPurchased(int modelQuantityPurchased) {
        this.modelQuantityPurchased = modelQuantityPurchased;
        return this;
    }

    public ItemBuilder setModelOriginalPrice(BigDecimal modelOriginalPrice) {
        this.modelOriginalPrice = modelOriginalPrice;
        return this;
    }

    public ItemBuilder setModelDiscountedPrice(BigDecimal modelDiscountedPrice) {
        this.modelDiscountedPrice = modelDiscountedPrice;
        return this;
    }

    public ItemBuilder setWholesale(boolean wholesale) {
        this.wholesale = wholesale;
        return this;
    }

    public ItemBuilder setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public ItemBuilder setAddOnDeal(boolean addOnDeal) {
        this.addOnDeal = addOnDeal;
        return this;
    }

    public ItemBuilder setMainItem(boolean mainItem) {
        this.mainItem = mainItem;
        return this;
    }

    public ItemBuilder setAddOnDealId(long addOnDealId) {
        this.addOnDealId = addOnDealId;
        return this;
    }

    public ItemBuilder setPromotionType(String promotionType) {
        this.promotionType = promotionType;
        return this;
    }

    public ItemBuilder setPromotionId(long promotionId) {
        this.promotionId = promotionId;
        return this;
    }

    public ItemBuilder setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
        return this;
    }

    public ItemBuilder setPromotionGroupId(long promotionGroupId) {
        this.promotionGroupId = promotionGroupId;
        return this;
    }

    public ItemBuilder setImageInfo(OrderShopee.ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
        return this;
    }

    public OrderShopee.Item createItem() {
        return new OrderShopee.Item(id, name, sku, modelId, modelName, modelSku, modelQuantityPurchased, modelOriginalPrice, modelDiscountedPrice, wholesale, weight, addOnDeal, mainItem, addOnDealId, promotionType, promotionId, orderItemId, promotionGroupId, imageInfo);
    }
}