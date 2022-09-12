package com.commercehub.etl.detail.usecaseinteractor.scheduler;

import com.commercehub.etl.core.usecase.linking.GetOneLinkPerShop;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskExecution;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskExecutor;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;

@Dependent
public class TimedTaskExecutionInteractor implements TimedTaskExecution {

    private final GetOneLinkPerShop getOneLinkPerShop;

    private final Instance<TimedTaskExecutor> timedTaskExecutors;

    public TimedTaskExecutionInteractor(GetOneLinkPerShop getOneLinkPerShop, Instance<TimedTaskExecutor> timedTaskExecutors) {
        this.getOneLinkPerShop = getOneLinkPerShop;
        this.timedTaskExecutors = timedTaskExecutors;
    }

    @Override
    public Multi<Boolean> execute() {
        return getOneLinkPerShop.execute()
                .flatMap(linking ->
                        Multi.createFrom()
                                .iterable(timedTaskExecutors)
                                .flatMap(executor -> executor.execute(linking).toMulti())
                );
    }

}
