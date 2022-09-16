package com.commercehub.linker.core.entitybuilder;

import com.commercehub.linker.core.entity.TimedTask;

import java.time.Instant;

public class TimedTaskBuilder {
    private String id;
    private String status;
    private String platform;
    private String shopId;
    private Instant paramTimeFrom;
    private Instant paramTimeTo;
    private Instant startTime;
    private Instant endTime;

    public TimedTaskBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public TimedTaskBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public TimedTaskBuilder setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public TimedTaskBuilder setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public TimedTaskBuilder setParamTimeFrom(Instant paramTimeFrom) {
        this.paramTimeFrom = paramTimeFrom;
        return this;
    }

    public TimedTaskBuilder setParamTimeTo(Instant paramTimeTo) {
        this.paramTimeTo = paramTimeTo;
        return this;
    }

    public TimedTaskBuilder setStartTime(Instant startTime) {
        this.startTime = startTime;
        return this;
    }

    public TimedTaskBuilder setEndTime(Instant endTime) {
        this.endTime = endTime;
        return this;
    }

    public TimedTask createTimedTask() {
        return new TimedTask(id, status, platform, shopId, paramTimeFrom, paramTimeTo, startTime, endTime);
    }
}