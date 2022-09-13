package com.commercehub.etl.detail.usecase.scheduler;

import com.commercehub.etl.core.usecase.scheduler.TimedTaskGroupNameProvider;

import javax.enterprise.context.Dependent;

@Dependent
public class TimedTaskGroupNameProviderInteractor implements TimedTaskGroupNameProvider {

    @Override
    public String provide(String taskName) {
        return "TT-" + taskName;
    }

}
