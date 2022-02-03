package com.commercehub.etl.domain.repository;

import com.commercehub.etl.domain.entity.schduler.TimedTask;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface TimedTaskRepository {

    TimedTask get(String collectionName, String documentId);

    TimedTask getLatest(String collectionName, String platform, String shopId);

    List<TimedTask> getAll(String collectionName, String platform, String shopId, String status);

    List<TimedTask> getAll(String collectionName, String platform, String shopId, String status, Instant maxTime, int noOfTaskToRun);

    boolean verifyLatestAndCreate(TimedTask latestTask, String collectionName, TimedTask... tasks);

    boolean updateToStart(String collectionName, String documentId, String status, Date startTime);

    boolean updateToEnd(String collectionName, String documentId, String status, Date endTime);

    boolean revertToPending(String collectionName, String documentId);

}
