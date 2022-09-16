package com.commercehub.linker.core.usecase.scheduler;

import com.commercehub.linker.core.entity.TimedTask;

import java.util.List;

public interface GetLastCreatedTask {

    List<TimedTask> execute(String taskGroup, String shopId, int noOfRecord);

}
