package com.commercehub.linker.detail.usecase.scheduler;

import com.commercehub.linker.core.entity.TimedTask;
import com.commercehub.linker.core.repository.TimedTaskRepository;
import com.commercehub.linker.core.usecase.scheduler.GetLastFailedTask;

import javax.enterprise.context.Dependent;
import java.util.List;

@Dependent
public class GetLastFailedTaskInteractor implements GetLastFailedTask {

    private final TimedTaskRepository repository;

    public GetLastFailedTaskInteractor(TimedTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TimedTask> execute(String taskGroup, String shopId, int noOfRecord) {
        return repository.getLastFailed(taskGroup, shopId, noOfRecord);
    }
}
