package com.commercehub.linker.core.usecase.scheduler;

import com.commercehub.linker.core.entity.TimedTask;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface GetTask {

    public static final String TYPE_LAST_CREATED = "lastCreated";
    public static final String TYPE_LAST_STARTED = "lastStarted";
    public static final String TYPE_LAST_COMPLETED = "lastCompleted";
    public static final String TYPE_LAST_FAILED = "lastFailed";
    public static final String TYPE_NEXT_PENDING = "nextPending";
    public static final String TYPE_LONG_RUNNING = "longRunning";

    public static final String REGEX_ALL_TYPE =
            TYPE_LAST_CREATED
                    + "|" + TYPE_LAST_STARTED
                    + "|" + TYPE_LAST_COMPLETED
                    + "|" + TYPE_LAST_FAILED
                    + "|" + TYPE_NEXT_PENDING
                    + "|" + TYPE_LONG_RUNNING;

    Uni<List<TimedTask>> execute(String taskGroupName, String shopId, String type);

}
