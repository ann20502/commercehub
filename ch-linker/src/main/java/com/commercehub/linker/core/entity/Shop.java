package com.commercehub.linker.core.entity;

public class Shop {

    public final String platform;
    public final String shopId;
    public final String shopName;
    public final String shopRegion;
    public final String shopStatus;

    public Shop(String platform, String shopId, String shopName, String shopRegion, String shopStatus) {
        this.platform = platform;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopRegion = shopRegion;
        this.shopStatus = shopStatus;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "platform='" + platform + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopRegion='" + shopRegion + '\'' +
                ", shopStatus='" + shopStatus + '\'' +
                '}';
    }
}
