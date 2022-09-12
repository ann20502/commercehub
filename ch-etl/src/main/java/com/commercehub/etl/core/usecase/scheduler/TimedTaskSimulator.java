package com.commercehub.etl.core.usecase.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface TimedTaskSimulator {

    Uni<TimedTask> simulateLatestTask(Linking linking, int minutePerTask);

    Uni<List<TimedTask>> simulateSubsequentTask(Linking linking, int minutePerTask, TimedTask latestTask, int noOfTaskToSimulate, int noOfDayInAdvance);

}
