package com.commercehub.etl.core.usecase.scheduler;

import io.smallrye.mutiny.Multi;

public interface TimedTaskExecution {

    Multi<Boolean> execute();

}
