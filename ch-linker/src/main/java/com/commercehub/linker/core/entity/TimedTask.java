package com.commercehub.linker.core.entity;

import java.time.Instant;

public class TimedTask {

    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_STARTED = "started";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_COMPLETED = "completed";

    public final String id;
    public final String status;
    public final String platform;
    public final String shopId;
    public final Instant paramTimeFrom;
    public final Instant paramTimeTo;
    public final Instant startTime;
    public final Instant endTime;

    public TimedTask(String id, String status, String platform, String shopId, Instant paramTimeFrom, Instant paramTimeTo, Instant startTime, Instant endTime) {
        this.id = id;
        this.status = status;
        this.platform = platform;
        this.shopId = shopId;
        this.paramTimeFrom = paramTimeFrom;
        this.paramTimeTo = paramTimeTo;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
