package com.commercehub.etl.detail.repository.scheduler;

import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.entitybuilder.scheduler.TimedTaskBuilder;

import java.time.Instant;

public class TimedTaskTransformer {

    public static TimedTask from(FSTimedTask task) {
        Instant paramTimeFrom = task.getParamTimeFrom() == null ? null : task.getParamTimeFrom().toInstant();
        Instant paramTimeTo = task.getParamTimeTo() == null ? null : task.getParamTimeTo().toInstant();
        Instant startTime = task.getStartTime() == null ? null : task.getStartTime().toInstant();
        Instant endTime = task.getEndTime() == null ? null : task.getEndTime().toInstant();

        return new TimedTaskBuilder()
                .setId(task.getId())
                .setPlatform(task.getPlatform())
                .setShopId(task.getShopId())
                .setStatus(task.getStatus())
                .setParamTimeFrom(paramTimeFrom)
                .setParamTimeTo(paramTimeTo)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .createTimedTask();
    }

}
