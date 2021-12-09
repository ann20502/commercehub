package com.commercehub.etl.domain.entity.schduler;

import com.commercehub.common.TimeZoneUtils;
import com.commercehub.common.TimedTaskUtils;
import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.repository.TimedTaskRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TimedTaskExecutor {

    @Inject
    Logger log;

    public Uni<List<TimedTask>> execute(Linking linking, String baseUri) {
        if ( !shallRun(linking) ) { return Uni.createFrom().item(new ArrayList<>()); }

        String collectionName = collectionName();

        // Get task by max time (calculate with buffer)
        Instant now = Instant.now();
        Duration DURATION_BUFFER = Duration.ofMinutes(30);
        Instant maxTime = now.minus(DURATION_BUFFER);

        List<TimedTask> pendingTasks = repository().getAll(
                collectionName, linking.getPlatform(), linking.getShopId(),
                TimedTask.STATUS_PENDING, maxTime
        );
        log.info("[" + pendingTasks.size() + "] pending triggers retrieved");

        return Multi.createFrom().iterable(pendingTasks)
                .onItem().call(task -> Uni.createFrom().nullItem().onItem().delayIt().by(Duration.ofSeconds(5)))
                .filter(task -> runnable().run(linking, task, baseUri))
                .map(task -> {
                    task.setStatus(TimedTask.STATUS_STARTED);
                    task.setStartTime(TimeZoneUtils.getDate(Instant.now().toEpochMilli()));
                    return task;
                })
                .filter(task -> repository().updateToStart(
                        collectionName, task.getId(), task.getStatus(), task.getStartTime()
                ))
                .collect().asList();

//        return pendingTasks.stream()
//                .filter(task -> runnable().run(linking, task))
//                .peek(task -> {
//                    task.setStatus(TimedTask.STATUS_STARTED);
//                    task.setStartTime(TimeZoneUtils.getDate(Instant.now().toEpochMilli()));
//                })
//                .filter(task -> repository().updateToStart(
//                        collectionName, task.getId(), task.getStatus(), task.getStartTime()
//                ))
//                .collect(Collectors.toList());
    }

    private String collectionName() {
        return TimedTaskUtils.COLLECTION_PREFIX + taskName();
    }

    public abstract boolean shallRun(Linking linking);
    public abstract TimedTaskRunnable runnable();
    public abstract String taskName();
    public abstract TimedTaskRepository repository();

}
