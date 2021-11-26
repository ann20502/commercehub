package com.commercehub.etl.domain.entity.schduler;

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

    private String platform;
    private String shopId;
    private String status;
    private Date paramTimeFrom;
    private Date paramTimeTo;
    private Date startTime;
    private Date endTime;

    public TimedTask() {}

    public TimedTask(TimedTask trigger) {
        this.id = trigger.id;
        this.platform = trigger.platform;
        this.shopId = trigger.shopId;
        this.status = trigger.status;
        this.paramTimeFrom = trigger.paramTimeFrom;
        this.paramTimeTo = trigger.paramTimeTo;
        this.startTime = trigger.startTime;
        this.endTime = trigger.endTime;
    }

    public TimedTask(String id, String platform, String shopId, String status, Date paramTimeFrom, Date paramTimeTo, Date startTime, Date endTime) {
        this.id = id;
        this.platform = platform;
        this.shopId = shopId;
        this.status = status;
        this.paramTimeFrom = paramTimeFrom;
        this.paramTimeTo = paramTimeTo;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", platform='" + platform + '\'' +
                ", shopId='" + shopId + '\'' +
                ", status='" + status + '\'' +
                ", paramTimeFrom=" + paramTimeFrom +
                ", paramTimeTo=" + paramTimeTo +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

}
