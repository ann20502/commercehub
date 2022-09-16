package com.commercehub.linker.detail.usecase.scheduler;

import com.commercehub.linker.core.entity.TimedTask;
import com.commercehub.linker.core.repository.TimedTaskRepository;
import com.commercehub.linker.core.usecase.scheduler.GetLastCompletedTask;

import javax.enterprise.context.Dependent;
import java.util.List;

@Dependent
public class GetLastCompletedTaskInteractor implements GetLastCompletedTask {

    private final TimedTaskRepository repository;

    public GetLastCompletedTaskInteractor(TimedTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TimedTask> execute(String taskGroup, String shopId, int noOfRecord) {
        return repository.getLastCompleted(taskGroup, shopId, noOfRecord);
    }

}
