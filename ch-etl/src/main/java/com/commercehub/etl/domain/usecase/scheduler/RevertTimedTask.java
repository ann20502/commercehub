package com.commercehub.etl.domain.usecase.scheduler;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.schduler.TimedTask;
import com.commercehub.etl.domain.entity.schduler.TimedTaskExecutor;
import com.commercehub.etl.domain.repository.TimedTaskRepository;
import com.google.cloud.Tuple;
import io.smallrye.mutiny.Multi;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class RevertTimedTask {

    @Inject
    @Any
    Instance<TimedTaskExecutor> executors;

    @Inject
    TimedTaskRepository repository;

    @Inject
    Logger log;

    public Multi<TimedTask> revert(Linking linking) {
        return Multi.createFrom().iterable(executors)
                .flatMap(executor -> {
                    final String COLLECTION_NAME = executor.collectionName();
                    List<TimedTask> tasks = repository.getAll(COLLECTION_NAME, linking.getPlatform(), linking.getShopId(), TimedTask.STATUS_ERROR);
                    log.info("[" + tasks.size() + "] error tasks retrieved from [" + executor.collectionName() + "][" + linking.getPlatform() + "][" + linking.getShopId() + "]");
                    return Multi.createFrom().iterable(tasks).map(task -> Tuple.of(COLLECTION_NAME, task));
                })
                .filter(nameAndTask -> {
                    final String COLLECTION_NAME = nameAndTask.x();
                    final TimedTask task = nameAndTask.y();
                    return repository.revertToPending(COLLECTION_NAME, task.getId());
                })
                .map(nameAndTask -> {
                    TimedTask task = nameAndTask.y();
                    task.setStatus(TimedTask.STATUS_PENDING);
                    task.setStartTime(null);
                    task.setEndTime(null);
                    return task;
                });
    }

}
