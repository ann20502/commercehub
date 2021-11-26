package com.commercehub.linker.domain.entity;

import java.util.Date;

public class ExistingLinking {

    private String id;
    private String platform;
    private String status;
    private String partnerId;
    private String shopId;
    private String shopName;
    private String shopRegion;
    private String shopStatus;
    private String logo;
    private boolean setup;
    private Date businessStartDate;

    public ExistingLinking(Linking linking) {
        this.id = linking.getId();
        this.platform = linking.getPlatform();
        this.status = linking.getStatus();
        this.partnerId = linking.getPartnerId();
        this.shopId = linking.getShopId();
        this.shopName = linking.getShopName();
        this.shopRegion = linking.getShopRegion();
        this.shopStatus = linking.getShopStatus();
        this.setup = linking.isSetup();
        this.businessStartDate = linking.getBusinessStartDate();
    }

    public String getId() {
        return id;
    }

    public String getPlatform() {
        return platform;
    }

    public String getStatus() {
        return status;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopRegion() {
        return shopRegion;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public boolean isSetup() {
        return setup;
    }

    public Date getBusinessStartDate() {
        return businessStartDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "ExistingLinking{" +
                "id='" + id + '\'' +
                ", platform='" + platform + '\'' +
                ", status='" + status + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopRegion='" + shopRegion + '\'' +
                ", shopStatus='" + shopStatus + '\'' +
                ", logo='" + logo + '\'' +
                ", setup=" + setup +
                ", businessStartDate=" + businessStartDate +
                '}';
    }

}
