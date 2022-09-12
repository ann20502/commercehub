package com.commercehub.etl.core.entity.scheduler;

import java.time.Instant;

public class TimedTask {

    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_STARTED = "started";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_COMPLETED = "completed";

    public final String id;
    public final String platform;
    public final String shopId;
    public final String status;
    public final Instant paramTimeFrom;
    public final Instant paramTimeTo;
    public final Instant startTime;
    public final Instant endTime;

    public TimedTask(String id, String platform, String shopId, String status, Instant paramTimeFrom, Instant paramTimeTo, Instant startTime, Instant endTime) {
        this.id = id;
        this.platform = platform;
        this.shopId = shopId;
        this.status = status;
        this.paramTimeFrom = paramTimeFrom;
        this.paramTimeTo = paramTimeTo;
        this.startTime = startTime;
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
