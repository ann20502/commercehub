package com.commercehub.linker.detail.usecase.scheduler;

import com.commercehub.linker.core.repository.TimedTaskGroupRepository;
import com.commercehub.linker.core.usecase.scheduler.GetTaskGroup;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import java.util.List;

@Dependent
public class GetTaskGroupInteractor implements GetTaskGroup {

    private final TimedTaskGroupRepository repository;

    public GetTaskGroupInteractor(TimedTaskGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public Uni<List<String>> getAll() {
        return Uni.createFrom().item(repository::getAllTaskGroup);
    }

}
