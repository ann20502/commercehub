package com.commercehub.etl.domain.entity.linking;

import java.util.Date;

public class LinkingSimplified {

    public final String id;
    public final String status;
    public final String platform;
    public final String shopId;
    public final String shopName;
    public final boolean link;
    public final boolean setup;
    public final Date businessStartDate;

    public LinkingSimplified(Linking linking) {
        this.id = linking.getId();
        this.status = linking.getStatus();
        this.platform = linking.getPlatform();
        this.shopId = linking.getShopId();
        this.shopName = linking.getShopName();
        this.link = linking.isLink();
        this.setup = linking.isSetup();
        this.businessStartDate = linking.getBusinessStartDate();
    }

    @Override
    public String toString() {
        return "LinkingSimplified{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", platform='" + platform + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", link=" + link +
                ", setup=" + setup +
                ", businessStartDate=" + businessStartDate +
                '}';
    }

}
