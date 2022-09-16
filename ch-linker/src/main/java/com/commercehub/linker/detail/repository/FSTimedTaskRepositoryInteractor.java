package com.commercehub.linker.detail.repository;

import com.commercehub.common.TimeZoneUtils;
import com.commercehub.linker.core.entity.TimedTask;
import com.commercehub.linker.core.repository.TimedTaskRepository;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSTimedTaskRepositoryInteractor implements TimedTaskRepository {

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public List<TimedTask> getAll(String collectionName, Date date, String platform, String shopId) {
        Date from = getBeginningOfTheDay(date);
        Date to = getEndOfTheDay(date);

        final Query query = firestore.collection(collectionName)
                .whereEqualTo("platform", platform)
                .whereEqualTo("shopId", shopId)
                .whereGreaterThanOrEqualTo("paramTimeFrom", from)
                .whereLessThanOrEqualTo("paramTimeFrom", to)
                .orderBy("paramTimeFrom", Query.Direction.ASCENDING);

        return executeQueryAndReturnParsedResult(query);
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
        final Query query = firestore.collection(collectionName)
                .whereEqualTo("shopId", shopId)
                .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                .limit(noOfRecord);

        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<TimedTask> getLastStarted(String collectionName, String shopId, int noOfRecord) {
        final Query query = firestore.collection(collectionName)
                .whereEqualTo("shopId", shopId)
                .whereEqualTo("status", TimedTask.STATUS_STARTED)
                .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                .limit(noOfRecord);

        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<TimedTask> getLastCompleted(String collectionName, String shopId, int noOfRecord) {
        final Query query = firestore.collection(collectionName)
                .whereEqualTo("shopId", shopId)
                .whereEqualTo("status", TimedTask.STATUS_COMPLETED)
                .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                .limit(noOfRecord);

        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<TimedTask> getLastFailed(String collectionName, String shopId, int noOfRecord) {
        final Query query = firestore.collection(collectionName)
                .whereEqualTo("shopId", shopId)
                .whereEqualTo("status", TimedTask.STATUS_ERROR)
                .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                .limit(noOfRecord);

        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<TimedTask> getNextPending(String collectionName, String shopId, int noOfRecord) {
        final Query query = firestore.collection(collectionName)
                .whereEqualTo("shopId", shopId)
                .whereEqualTo("status", TimedTask.STATUS_PENDING)
                .orderBy("paramTimeFrom", Query.Direction.ASCENDING)
                .limit(noOfRecord);

        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<TimedTask> getLongRunning(String collectionName, String shopId, boolean hasComplete, int noOfRecord) {
        final Instant now = Instant.now();
        final Duration longRunningDuration = Duration.ofMinutes(30);
        Query query = firestore.collection(collectionName).whereEqualTo("shopId", shopId);

        if (hasComplete) {
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

        query = query.limit(noOfRecord);
        return executeQueryAndReturnParsedResult(query);
    }

    private List<TimedTask> executeQueryAndReturnParsedResult(Query query) {
        try {
            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> snapshot.toObject(FSTimedTask.class))
                    .map(TimedTaskTransformer::from)
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException ex) {
            log.error("Failed to retrieve timed task: " + ex.getMessage());
        }

        return Collections.emptyList();
    }

}
