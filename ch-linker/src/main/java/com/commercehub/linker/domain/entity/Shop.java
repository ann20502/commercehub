package com.commercehub.linker.domain.entity;

public class Shop {

    public final String shopId;
    public final String shopName;
    public final String shopRegion;
    public final String platform;

    public Shop(String shopId, String shopName, String shopRegion, String platform) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopRegion = shopRegion;
        this.platform = platform;
    }

}
