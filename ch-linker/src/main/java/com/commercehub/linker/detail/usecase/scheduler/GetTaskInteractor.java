package com.commercehub.linker.detail.usecase.scheduler;

import com.commercehub.linker.core.entity.TimedTask;
import com.commercehub.linker.core.usecase.scheduler.*;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class GetTaskInteractor implements GetTask {

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

    @Override
    public Uni<List<TimedTask>> execute(String taskGroupName, String shopId, String type) {
        return Uni.createFrom().item(
                () -> {
                    switch (type) {
                        case GetTask.TYPE_LAST_CREATED:
                            return getLastCreatedTask.execute(taskGroupName, shopId, 5);

                        case GetTask.TYPE_LAST_STARTED:
                            return getLastStartedTask.execute(taskGroupName, shopId, 5);

                        case GetTask.TYPE_LAST_COMPLETED:
                            return getLastCompletedTask.execute(taskGroupName, shopId, 5);

                        case GetTask.TYPE_LAST_FAILED:
                            return getLastFailedTask.execute(taskGroupName, shopId, 5);

                        case GetTask.TYPE_NEXT_PENDING:
                            return getNextPendingTask.execute(taskGroupName, shopId, 5);

                        case GetTask.TYPE_LONG_RUNNING:
                            return getLongRunningTask.execute(taskGroupName, shopId, false, 10);

                        default:
                            throw new RuntimeException("Unknown type: " + type);
                    }
                }
        );
    }

}
