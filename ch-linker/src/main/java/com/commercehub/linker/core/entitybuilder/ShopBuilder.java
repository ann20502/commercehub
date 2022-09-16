package com.commercehub.linker.core.entitybuilder;

import com.commercehub.linker.core.entity.Shop;

public class ShopBuilder {
    private String platform;
    private String shopId;
    private String shopName;
    private String shopRegion;
    private String shopStatus;

    public ShopBuilder setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public ShopBuilder setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public ShopBuilder setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public ShopBuilder setShopRegion(String shopRegion) {
        this.shopRegion = shopRegion;
        return this;
    }

    public ShopBuilder setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
        return this;
    }

    public Shop createShop() {
        return new Shop(platform, shopId, shopName, shopRegion, shopStatus);
    }
}