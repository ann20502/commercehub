package com.commercehub.linker.core.repository;

import com.commercehub.linker.core.entity.TimedTask;

import java.util.Date;
import java.util.List;

public interface TimedTaskRepository {

    // Does not abide time zone, hence not meaningful...
    List<TimedTask> getAll(String collectionName, Date date, String platform, String shopId);

    List<TimedTask> getLastCreated(String collectionName, String shopId, int noOfRecord);

    List<TimedTask> getLastStarted(String collectionName, String shopId, int noOfRecord);

    List<TimedTask> getLastCompleted(String collectionName, String shopId, int noOfRecord);

    List<TimedTask> getLastFailed(String collectionName, String shopId, int noOfRecord);

    List<TimedTask> getNextPending(String collectionName, String shopId, int noOfRecord);

    List<TimedTask> getLongRunning(String collectionName, String shopId, boolean hasComplete, int noOfRecord);

}
