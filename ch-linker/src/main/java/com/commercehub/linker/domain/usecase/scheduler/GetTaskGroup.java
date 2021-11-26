package com.commercehub.linker.domain.usecase.scheduler;

import com.commercehub.linker.domain.repository.TimedTaskGroupRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class GetTaskGroup {

    @Inject
    TimedTaskGroupRepository repository;

    public List<String> get() {
        return repository.getAllTaskGroup();
    }

}
