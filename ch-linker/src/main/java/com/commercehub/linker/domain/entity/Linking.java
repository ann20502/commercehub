package com.commercehub.linker.domain.entity;

public class Linking {

    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_INACTIVE = "I";

    private String platform;

    private String status;

    private String shopId;

    private String shopName;

    private String logo;

    public Linking() {}

    public Linking(String platform, String status, String shopId, String shopName, String logo) {
        this.platform = platform;
        this.status = status;
        this.shopId = shopId;
        this.shopName = shopName;
        this.logo = logo;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
