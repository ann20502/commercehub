package com.commercehub.linker.detail.usecase.scheduler;

import com.commercehub.linker.core.entity.TimedTask;
import com.commercehub.linker.core.repository.TimedTaskRepository;
import com.commercehub.linker.core.usecase.scheduler.GetLastCreatedTask;

import javax.enterprise.context.Dependent;
import java.util.List;

@Dependent
public class GetLastCreatedTaskInteractor implements GetLastCreatedTask {

    private final TimedTaskRepository repository;

    public GetLastCreatedTaskInteractor(TimedTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TimedTask> execute(String taskGroup, String shopId, int noOfRecord) {
        return repository.getLastCreated(taskGroup, shopId, noOfRecord);
    }

}
