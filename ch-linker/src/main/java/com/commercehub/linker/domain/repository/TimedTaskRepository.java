package com.commercehub.linker.domain.repository;

import com.commercehub.linker.domain.entity.TimedTask;

import java.util.Date;
import java.util.List;

public interface TimedTaskRepository {

    List<TimedTask> get(String collectionName, Date date, String platform, String shopId);

    List<TimedTask> getLastCreated(String collectionName, String shopId, int noOfRecord);

    List<TimedTask> getLastStarted(String collectionName, String shopId, int noOfRecord);

    List<TimedTask> getLastCompleted(String collectionName, String shopId, int noOfRecord);

    List<TimedTask> getLastFailed(String collectionName, String shopId, int noOfRecord);

    List<TimedTask> getNextPending(String collectionName, String shopId, int noOfRecord);

    List<TimedTask> getLongRunning(String collectionName, String shopId, boolean hasComplete, int noOfRecord);

}
