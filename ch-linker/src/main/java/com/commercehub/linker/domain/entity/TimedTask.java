package com.commercehub.linker.domain.entity;

import com.google.cloud.firestore.annotation.Exclude;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class TimedTask {

    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_STARTED = "started";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_COMPLETED = "completed";

    @Exclude
    private String id;

    private String status;
    private String platform;
    private String shopId;
    private Date paramTimeFrom;
    private Date paramTimeTo;
    private Date startTime;
    private Date endTime;

    @Exclude
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
        return "TimedTask{" +
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
