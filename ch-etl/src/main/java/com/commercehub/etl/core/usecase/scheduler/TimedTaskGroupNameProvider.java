package com.commercehub.etl.core.usecase.scheduler;

public interface TimedTaskGroupNameProvider {

    String provide(String taskName);

}
