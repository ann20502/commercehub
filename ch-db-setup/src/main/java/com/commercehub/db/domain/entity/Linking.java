package com.commercehub.db.domain.entity;

import com.google.cloud.firestore.annotation.Exclude;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class Linking {

    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_INACTIVE = "I";

    @Exclude
    private String id;

    private String status;
    private String platform;
    private String partnerId;
    private String shopId;
    private String shopName;
    private String shopStatus;
    private String shopRegion;
    private String accessToken;
    private Date accessTokenExpiry;
    private String refreshToken;
    private Date refreshTokenExpiry;
    private boolean link;
    private boolean setup;
    private Date businessStartDate;

    public Linking() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
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

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getShopRegion() {
        return shopRegion;
    }

    public void setShopRegion(String shopRegion) {
        this.shopRegion = shopRegion;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getAccessTokenExpiry() {
        return accessTokenExpiry;
    }

    public void setAccessTokenExpiry(Date accessTokenExpiry) {
        this.accessTokenExpiry = accessTokenExpiry;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getRefreshTokenExpiry() {
        return refreshTokenExpiry;
    }

    public void setRefreshTokenExpiry(Date refreshTokenExpiry) {
        this.refreshTokenExpiry = refreshTokenExpiry;
    }

    public boolean isLink() {
        return link;
    }

    public void setLink(boolean link) {
        this.link = link;
    }

    public boolean isSetup() {
        return setup;
    }

    public void setSetup(boolean setup) {
        this.setup = setup;
    }

    public Date getBusinessStartDate() {
        return businessStartDate;
    }

    public void setBusinessStartDate(Date businessStartDate) {
        this.businessStartDate = businessStartDate;
    }

    @Override
    public String toString() {
        return "Linking{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", platform='" + platform + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopStatus='" + shopStatus + '\'' +
                ", shopRegion='" + shopRegion + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", accessTokenExpiry=" + accessTokenExpiry +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenExpiry=" + refreshTokenExpiry +
                ", link=" + link +
                ", setup=" + setup +
                ", businessStartDate=" + businessStartDate +
                '}';
    }

}
