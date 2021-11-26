package com.commercehub.etl.vm;

import com.commercehub.etl.domain.entity.schduler.TimedTask;
import com.commercehub.etl.domain.usecase.scheduler.DefineTimedTask;
import com.commercehub.etl.domain.usecase.scheduler.ExecuteTimedTask;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class SchedulerViewModel {

    @Inject
    DefineTimedTask defineTimedTask;

    @Inject
    ExecuteTimedTask executeTimedTask;

    public Multi<List<TimedTask>> defineTimedTaskTrigger() {
        return defineTimedTask.execute();
    }

    public Multi<List<TimedTask>> executeTimedTaskTrigger() {
        return executeTimedTask.execute();
    }

}
