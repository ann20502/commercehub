package com.commercehub.linker.detail.repository;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class FSTimedTask {

    @DocumentId
    private String id;
    private String status;
    private String platform;
    private String shopId;
    private Date paramTimeFrom;
    private Date paramTimeTo;
    private Date startTime;
    private Date endTime;

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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Date getParamTimeFrom() {
        return paramTimeFrom;
    }

    public void setParamTimeFrom(Date paramTimeFrom) {
        this.paramTimeFrom = paramTimeFrom;
    }

    public Date getParamTimeTo() {
        return paramTimeTo;
    }

    public void setParamTimeTo(Date paramTimeTo) {
        this.paramTimeTo = paramTimeTo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "FSTimedTask{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", platform='" + platform + '\'' +
                ", shopId='" + shopId + '\'' +
                ", paramTimeFrom=" + paramTimeFrom +
                ", paramTimeTo=" + paramTimeTo +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
