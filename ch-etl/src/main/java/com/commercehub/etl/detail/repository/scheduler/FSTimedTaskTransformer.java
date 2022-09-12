package com.commercehub.etl.detail.repository.scheduler;

import com.commercehub.etl.core.entity.scheduler.TimedTask;

import java.util.Date;

public class FSTimedTaskTransformer {

    public static FSTimedTask from(TimedTask task) {
        Date paramTimeFrom = task.paramTimeFrom == null ? null : Date.from(task.paramTimeFrom);
        Date paramTimeTo = task.paramTimeTo == null ? null : Date.from(task.paramTimeTo);
        Date startTime = task.startTime == null ? null : Date.from(task.startTime);
        Date endTime = task.endTime == null ? null : Date.from(task.endTime);

        return new FSTimedTaskBuilder()
                .setId(task.id)
                .setPlatform(task.platform)
                .setShopId(task.shopId)
                .setStatus(task.status)
                .setParamTimeFrom(paramTimeFrom)
                .setParamTimeTo(paramTimeTo)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .createFSTimedTask();
    }

}
