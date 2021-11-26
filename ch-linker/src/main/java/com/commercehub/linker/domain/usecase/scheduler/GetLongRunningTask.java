package com.commercehub.linker.domain.usecase.scheduler;

import com.commercehub.linker.domain.entity.TimedTask;
import com.commercehub.linker.domain.repository.TimedTaskRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class GetLongRunningTask {

    @Inject
    TimedTaskRepository repository;

    public List<TimedTask> get(String taskGroup, String shopId, boolean hasComplete, int noOfRecord) {
        return repository.getLongRunning(taskGroup, shopId, hasComplete, noOfRecord);
    }

}
