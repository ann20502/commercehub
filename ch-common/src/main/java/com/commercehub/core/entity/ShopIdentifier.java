package com.commercehub.core.entity;

import java.util.Objects;

public class ShopIdentifier {

    public final String platform;
    public final String shopId;

    public ShopIdentifier(String platform, String shopId) {
        this.platform = platform;
        this.shopId = shopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopIdentifier that = (ShopIdentifier) o;
        return Objects.equals(platform, that.platform) && Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(platform, shopId);
    }

}
