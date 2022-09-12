package com.commercehub.etl.detail.usecaseinteractor.scheduler;

import com.commercehub.common.Utils;
import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.repository.TimedTaskRepository;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskCreator;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskGroupNameProvider;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskSimulator;
import com.google.cloud.Tuple;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import java.util.List;

@Dependent
public abstract class TimedTaskCreatorBasedOnBusinessStartDateAndLatestTask implements TimedTaskCreator {

    private final TimedTaskGroupNameProvider groupNameProvider;
    private final TimedTaskSimulator timedTaskSimulator;

    public TimedTaskCreatorBasedOnBusinessStartDateAndLatestTask(TimedTaskGroupNameProvider groupNameProvider, TimedTaskSimulator timedTaskSimulator) {
        this.groupNameProvider = groupNameProvider;
        this.timedTaskSimulator = timedTaskSimulator;
    }

    @Override
    public Uni<Boolean> create(Linking input) {
        return Multi.createFrom().item(input)
                .filter(this::shallRun)
                .toUni()
                .flatMap(linking ->
                        Uni.createFrom()
                                .item(
                                        () -> repository()
                                                .getLatestOnly(collectionName(), linking.platform, linking.shopId))
                                .flatMap(
                                        latestTasks -> latestTasks.isEmpty() ?
                                                timedTaskSimulator.simulateLatestTask(linking, minutePerTask())
                                                : Uni.createFrom().item(latestTasks.get(Utils.POS_ZERO)))
                                .flatMap(
                                        latestTask -> timedTaskSimulator
                                                .simulateSubsequentTask(linking, minutePerTask(), latestTask, maxTaskToCreateAtATime(), noOfDayInAdvance())
                                                .map(tasks -> Tuple.of(latestTask, tasks)))
                                .map(
                                        latestTaskAndTaskToCreate -> {
                                            TimedTask latest = latestTaskAndTaskToCreate.x();
                                            List<TimedTask> taskToCreate = latestTaskAndTaskToCreate.y();
                                            TimedTask[] toGenerate = taskToCreate.toArray(new TimedTask[0]);
                                            return repository().createIfLatest(latest.id, latest.platform, latest.shopId, collectionName(), toGenerate);
                                        })
                );
    }

    private String collectionName() {
        return groupNameProvider.provide(name());
    }

    public abstract boolean shallRun(Linking linking);

    public abstract int minutePerTask();

    public abstract int maxTaskToCreateAtATime();

    public abstract int noOfDayInAdvance();

    public abstract TimedTaskRepository repository();

}
