package com.commercehub.linker.detail.repository;

import com.commercehub.linker.core.entity.TimedTask;
import com.commercehub.linker.core.entitybuilder.TimedTaskBuilder;

import java.time.Instant;

public class TimedTaskTransformer {

    public static TimedTask from(FSTimedTask task) {
        Instant startTime  = task.getStartTime() == null ? null : task.getStartTime().toInstant();
        Instant endTime = task.getEndTime() == null ? null : task.getEndTime().toInstant();

        return new TimedTaskBuilder()
                .setId(task.getId())
                .setStatus(task.getStatus())
                .setPlatform(task.getPlatform())
                .setShopId(task.getShopId())
                .setParamTimeFrom(task.getParamTimeFrom().toInstant())
                .setParamTimeTo(task.getParamTimeTo().toInstant())
                .setStartTime(startTime)
                .setEndTime(endTime)
                .createTimedTask();
    }

}
