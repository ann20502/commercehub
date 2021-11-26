package com.commercehub.linker.vm;

import com.commercehub.linker.domain.entity.TimedTask;
import com.commercehub.linker.domain.usecase.scheduler.*;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class SchedulerViewModel {

    @Inject
    GetLastCreatedTask getLastCreatedTask;

    @Inject
    GetLastStartedTask getLastStartedTask;

    @Inject
    GetLastCompletedTask getLastCompletedTask;

    @Inject
    GetLastFailedTask getLastFailedTask;

    @Inject
    GetNextPendingTask getNextPendingTask;

    @Inject
    GetLongRunningTask getLongRunningTask;

    @Inject
    GetTaskGroup getTaskGroup;

    public Uni<List<TimedTask>> getTask(String taskGroup, String shopId, String type) {
        return Uni.createFrom().item(type)
                .map(typeSelected -> {
                    switch (typeSelected) {
                        case "lastCreated":
                            return getLastCreatedTask.get(taskGroup, shopId, 5);

                        case "lastStarted":
                            return getLastStartedTask.get(taskGroup, shopId, 5);

                        case "lastCompleted":
                            return getLastCompletedTask.get(taskGroup, shopId, 5);

                        case "lastFailed":
                            return getLastFailedTask.get(taskGroup, shopId, 5);

                        case "nextPending":
                            return getNextPendingTask.get(taskGroup, shopId, 5);

                        case "longRunning":
                            return getLongRunningTask.get(taskGroup, shopId, false, 10);

                        default:
                            throw new RuntimeException("Unknown type: " + type);
                    }
                });
    }

    public Uni<List<String>> getTaskGroup() {
        return Uni.createFrom().item(() -> getTaskGroup.get());
    }

}
