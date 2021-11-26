package com.commercehub.linker.domain.repository;

import com.commercehub.common.TimeZoneUtils;
import com.commercehub.linker.domain.entity.TimedTask;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSTimedTaskRepository implements TimedTaskRepository {

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public List<TimedTask> get(String collectionName, Date date, String platform, String shopId) {
        try {
            Date from = getBeginningOfTheDay(date);
            Date to = getEndOfTheDay(date);

            final Query query = firestore.collection(collectionName)
                    .whereEqualTo("platform", platform)
                    .whereEqualTo("shopId", shopId)
                    .whereGreaterThanOrEqualTo("paramTimeFrom", from)
                    .whereLessThanOrEqualTo("paramTimeFrom", to)
                    .orderBy("paramTimeFrom", Query.Direction.ASCENDING);

            return query
                    .get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        TimedTask task = snapshot.toObject(TimedTask.class);
                        task.setId(snapshot.getId());
                        return task;
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            throw new RuntimeException("Failed to retrieve latest task: " + ex.getMessage());
        }
    }

    private Date getBeginningOfTheDay(Date date) {
        LocalDate localDate = TimeZoneUtils.getDate(date);
        return TimeZoneUtils.getDate(LocalDateTime.of(localDate, LocalTime.MIN));
    }

    private Date getEndOfTheDay(Date date) {
        LocalDate localDate = TimeZoneUtils.getDate(date);
        return TimeZoneUtils.getDate(LocalDateTime.of(localDate, LocalTime.MAX));
    }

    @Override
    public List<TimedTask> getLastCreated(String collectionName, String shopId, int noOfRecord) {
        try {
            final Query query = firestore.collection(collectionName)
                    .whereEqualTo("shopId", shopId)
                    .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                    .limit(noOfRecord);

            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        TimedTask task = snapshot.toObject(TimedTask.class);
                        task.setId(snapshot.getId());
                        return task;
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            throw new RuntimeException("Failed to retrieve last created task : " + ex.getMessage());
        }
    }

    @Override
    public List<TimedTask> getLastStarted(String collectionName, String shopId, int noOfRecord) {
        try {
            final Query query = firestore.collection(collectionName)
                    .whereEqualTo("shopId", shopId)
                    .whereEqualTo("status", TimedTask.STATUS_STARTED)
                    .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                    .limit(noOfRecord);

            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        TimedTask task = snapshot.toObject(TimedTask.class);
                        task.setId(snapshot.getId());
                        return task;
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            throw new RuntimeException("Failed to retrieve last started task : " + ex.getMessage());
        }
    }

    @Override
    public List<TimedTask> getLastCompleted(String collectionName, String shopId, int noOfRecord) {
        try {
            final Query query = firestore.collection(collectionName)
                    .whereEqualTo("shopId", shopId)
                    .whereEqualTo("status", TimedTask.STATUS_COMPLETED)
                    .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                    .limit(noOfRecord);

            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        TimedTask task = snapshot.toObject(TimedTask.class);
                        task.setId(snapshot.getId());
                        return task;
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            throw new RuntimeException("Failed to retrieve last completed task : " + ex.getMessage());
        }
    }

    @Override
    public List<TimedTask> getLastFailed(String collectionName, String shopId, int noOfRecord) {
        try {
            final Query query = firestore.collection(collectionName)
                    .whereEqualTo("shopId", shopId)
                    .whereEqualTo("status", TimedTask.STATUS_ERROR)
                    .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                    .limit(noOfRecord);

            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        TimedTask task = snapshot.toObject(TimedTask.class);
                        task.setId(snapshot.getId());
                        return task;
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            throw new RuntimeException("Failed to retrieve last failed task : " + ex.getMessage());
        }
    }

    @Override
    public List<TimedTask> getNextPending(String collectionName, String shopId, int noOfRecord) {
        try {
            final Query query = firestore.collection(collectionName)
                    .whereEqualTo("shopId", shopId)
                    .whereEqualTo("status", TimedTask.STATUS_PENDING)
                    .orderBy("paramTimeFrom", Query.Direction.ASCENDING)
                    .limit(noOfRecord);

            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        TimedTask task = snapshot.toObject(TimedTask.class);
                        task.setId(snapshot.getId());
                        return task;
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            throw new RuntimeException("Failed to retrieve next pending task : " + ex.getMessage());
        }
    }

    @Override
    public List<TimedTask> getLongRunning(String collectionName, String shopId, boolean hasComplete, int noOfRecord) {
        try {
            final Instant now = Instant.now();
            final Duration longRunningDuration = Duration.ofMinutes(30);
            Query query = firestore.collection(collectionName).whereEqualTo("shopId", shopId);

            if ( hasComplete ) {
                query = query
                        .whereEqualTo("status", TimedTask.STATUS_COMPLETED)
                        .whereGreaterThanOrEqualTo("duration", longRunningDuration.getSeconds())
                        .orderBy("duration", Query.Direction.ASCENDING)
                        .orderBy("paramTimeFrom", Query.Direction.ASCENDING);
            } else {
                Instant target = now.minus(longRunningDuration);
                query = query
                        .whereEqualTo("status", TimedTask.STATUS_STARTED)
                        .whereLessThanOrEqualTo("startTime", Date.from(target))
                        .orderBy("startTime", Query.Direction.ASCENDING)
                        .orderBy("paramTimeFrom", Query.Direction.ASCENDING);
            }

            query = query
                    .limit(noOfRecord);

            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        TimedTask task = snapshot.toObject(TimedTask.class);
                        task.setId(snapshot.getId());
                        return task;
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            throw new RuntimeException("Failed to retrieve long running task : " + ex.getMessage());
        }
    }

}
