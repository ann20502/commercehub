package com.commercehub.etl.detail.repository.scheduler;

import java.util.Date;

public class FSTimedTaskBuilder {
    private String id;
    private String platform;
    private String shopId;
    private String status;
    private Date paramTimeFrom;
    private Date paramTimeTo;
    private Date startTime;
    private Date endTime;

    public FSTimedTaskBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public FSTimedTaskBuilder setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public FSTimedTaskBuilder setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public FSTimedTaskBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public FSTimedTaskBuilder setParamTimeFrom(Date paramTimeFrom) {
        this.paramTimeFrom = paramTimeFrom;
        return this;
    }

    public FSTimedTaskBuilder setParamTimeTo(Date paramTimeTo) {
        this.paramTimeTo = paramTimeTo;
        return this;
    }

    public FSTimedTaskBuilder setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public FSTimedTaskBuilder setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public FSTimedTask createFSTimedTask() {
        return new FSTimedTask(id, platform, shopId, status, paramTimeFrom, paramTimeTo, startTime, endTime);
    }
}