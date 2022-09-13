package com.commercehub.etl.detail.usecase.scheduler;

import com.commercehub.common.TimedTaskUtils;
import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.repository.TimedTaskRepository;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskExecutor;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskRunnable;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import java.time.Duration;
import java.time.Instant;

@Dependent
public abstract class TimedTaskExecutorWithDelay implements TimedTaskExecutor {

    @ConfigProperty(name = "commercehub.etl.timed-task.executor.no-of-task-to-run", defaultValue = "10")
    int noOfTaskToRun;

    private final Duration FIVE_SECONDS = Duration.ofSeconds(5);

    // Injects UrlInfo after DELAY will cause null pointer exception as the thread switch lose the context
    @Override
    public Uni<Boolean> execute(Linking linking) {
        return Multi.createFrom()
                .item(
                        () -> repository().getAll(
                                collectionName(),
                                linking.platform,
                                linking.shopId,
                                TimedTask.STATUS_PENDING,
                                maxTime(),
                                noOfTaskToRun
                        ))
                .flatMap(tasks -> Multi.createFrom().iterable(tasks))
                .onItem().call(task -> Uni.createFrom().nullItem().onItem().delayIt().by(FIVE_SECONDS))
                .filter(task -> runnable().run(linking, task))
                .filter(
                        task -> repository().setToStart(
                                collectionName(),
                                task.id,
                                Instant.now()
                        ))
                .collect().asList()
                .map(results -> Boolean.TRUE);
    }

    private String collectionName() {
        return TimedTaskUtils.COLLECTION_PREFIX + name();
    }

    private Instant maxTime() { return Instant.now().minus(buffer()); }

    public abstract boolean shallRun(Linking linking);
    public abstract TimedTaskRunnable runnable();
    public abstract TimedTaskRepository repository();
    public abstract Duration buffer();

}
