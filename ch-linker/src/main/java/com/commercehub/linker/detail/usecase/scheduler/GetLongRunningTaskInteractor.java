package com.commercehub.linker.detail.usecase.scheduler;

import com.commercehub.linker.core.entity.TimedTask;
import com.commercehub.linker.core.repository.TimedTaskRepository;
import com.commercehub.linker.core.usecase.scheduler.GetLongRunningTask;

import javax.enterprise.context.Dependent;
import java.util.List;

@Dependent
public class GetLongRunningTaskInteractor implements GetLongRunningTask {

    private final TimedTaskRepository repository;

    public GetLongRunningTaskInteractor(TimedTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TimedTask> execute(String taskGroup, String shopId, boolean hasComplete, int noOfRecord) {
        return repository.getLongRunning(taskGroup, shopId, hasComplete, noOfRecord);
    }

}
