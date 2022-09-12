package com.commercehub.etl.core.usecase.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;

public interface TimedTaskRunnable {

    boolean run(Linking linking, TimedTask task);

}
