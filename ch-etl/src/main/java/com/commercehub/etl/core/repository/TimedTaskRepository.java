package com.commercehub.etl.core.repository;

import com.commercehub.etl.core.entity.scheduler.TimedTask;

import java.time.Instant;
import java.util.List;

public interface TimedTaskRepository {

    List<TimedTask> getSingle(String collectionName, String documentId);

    List<TimedTask> getLatestOnly(String collectionName, String platform, String shopId);

    List<TimedTask> getAll(String collectionName, String platform, String shopId, String status);

    List<TimedTask> getAll(String collectionName, String platform, String shopId, String status, Instant maxTime, int noOfTaskToRun);

    boolean createIfLatest(String latestDocumentId, String platform, String shopId, String collectionName, TimedTask... taskToCreate);

    boolean setToStart(String collectionName, String documentId, Instant startTime);

    boolean setToEnd(String collectionName, String documentId, Instant endTime);

    boolean setToPending(String collectionName, String documentId);

}
