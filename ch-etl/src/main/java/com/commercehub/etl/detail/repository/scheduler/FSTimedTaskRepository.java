package com.commercehub.etl.detail.repository.scheduler;

import com.commercehub.common.Utils;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.repository.TimedTaskRepository;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSTimedTaskRepository implements TimedTaskRepository {

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public List<TimedTask> getSingle(String collectionName, String documentId) {
        try {
            final DocumentReference documentReference = firestore.collection(collectionName).document(documentId);
            DocumentSnapshot snapshot = documentReference.get().get();
            TimedTask task = transform(snapshot);
            if ( task != null ) { return List.of(task); }
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve timed task: " + ex.getMessage());
        }
        return Collections.emptyList();
    }

    private TimedTask transform(DocumentSnapshot snapshot) {
        FSTimedTask task = snapshot.toObject(FSTimedTask.class);
        return task == null ? null : TimedTaskTransformer.from(task);
    }

    @Override
    public List<TimedTask> getLatestOnly(String collectionName, String platform, String shopId) {
        final Query query = firestore.collection(collectionName)
                .whereEqualTo("platform", platform)
                .whereEqualTo("shopId", shopId)
                .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                .limit(1);

        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<TimedTask> getAll(String collectionName, String platform, String shopId, String status) {
        final Query query = firestore.collection(collectionName)
                .whereEqualTo("platform", platform)
                .whereEqualTo("shopId", shopId)
                .whereEqualTo("status", status)
                .orderBy("paramTimeFrom", Query.Direction.ASCENDING)
                .limit(20);

        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<TimedTask> getAll(String collectionName, String platform, String shopId, String status, Instant maxTime, int noOfTaskToRun) {
        Date finalMaxTime = Date.from(maxTime);

        final Query query = firestore.collection(collectionName)
                .whereEqualTo("platform", platform)
                .whereEqualTo("shopId", shopId)
                .whereEqualTo("status", status)
                .whereLessThanOrEqualTo("paramTimeTo", finalMaxTime)
                .orderBy("paramTimeTo", Query.Direction.ASCENDING)
                .limit(noOfTaskToRun);

        return executeQueryAndReturnParsedResult(query);
    }

    private List<TimedTask> executeQueryAndReturnParsedResult(Query query) {
        try {
            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> snapshot.toObject(FSTimedTask.class))
                    .map(TimedTaskTransformer::from)
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve timed task: " + ex.getMessage());
        }

        return Collections.emptyList();
    }

    @Override
    public boolean createIfLatest(
            String latestDocumentId,
            String platform,
            String shopId,
            String collectionName,
            TimedTask... tasks
    ) {
        try {
            final CollectionReference collectionReference = firestore.collection(collectionName);
            return firestore.runTransaction(transaction -> {
                // Check if latest
                if ( latestDocumentId != null && !latestDocumentId.isEmpty() ) {
                    final Query query =
                            firestore.collection(collectionName)
                                    .whereEqualTo("shopId", shopId)
                                    .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                                    .limit(1);

                    List<QueryDocumentSnapshot> queryDocumentSnapshots = transaction.get(query).get().getDocuments();
                    if ( queryDocumentSnapshots.isEmpty()
                            || !latestDocumentId.equals(queryDocumentSnapshots.get(Utils.POS_ZERO).getId()) ) {
                        log.error("Task id " + latestDocumentId + " is no longer the latest, request reject!");
                        return false;
                    }
                }

                for (TimedTask task : tasks) {
                    Map<String,Object> additionalTaskField = getAdditionalTaskField(task);
                    FSTimedTask timedTask = FSTimedTaskTransformer.from(task);
                    DocumentReference documentReference = collectionReference.document();
                    transaction
                            .set(documentReference, timedTask)
                            .update(documentReference, additionalTaskField);
                }

                log.info("[" + tasks.length + "] tasks added to [" + collectionName + "]");
                return true;
            }).get();
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to save tasks: " + ex.getMessage());
            return false;
        }
    }

    private Map<String,Object> getAdditionalTaskField(TimedTask task) {
        Map<String,Object> result = new HashMap<>();
        result.put("paramTimeFromMillis", task.paramTimeFrom.toEpochMilli());
        result.put("paramTimeToMillis", task.paramTimeTo.toEpochMilli());
        return result;
    }

    @Override
    public boolean setToStart(String collectionName, String documentId, Instant startTime) {
        try {
            Map<String,Object> fields = getFieldForSetToStart(startTime);
            Timestamp timestamp = firestore.collection(collectionName)
                    .document(documentId)
                    .update(fields)
                    .get().getUpdateTime();
            log.info("Task [" + documentId + "] updated at: " + timestamp);
            return true;
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to update task: " + ex.getMessage());
            return false;
        }
    }

    private Map<String,Object> getFieldForSetToStart(Instant startTime) {
        Map<String,Object> result = new HashMap<>();
        result.put("status", TimedTask.STATUS_STARTED);
        result.put("startTime", Date.from(startTime));
        result.put("startTimeMillis", startTime.toEpochMilli());
        return result;
    }

    @Override
    public boolean setToEnd(String collectionName, String documentId, Instant endTime) {
        try {
            final DocumentReference documentReference = firestore.collection(collectionName).document(documentId);
            return firestore.runTransaction(transaction -> {
                FSTimedTask task = transaction.get(documentReference).get().toObject(FSTimedTask.class);
                if ( task == null ) {
                    log.error("Invalid document: " + collectionName + "/" + documentId);
                    return false;
                }

                long startTimeMillis = task.getStartTime().getTime();
                long endTimeMillis = endTime.toEpochMilli();
                Duration duration = Duration.ofMillis(endTimeMillis - startTimeMillis);
                long durationSeconds = duration.toSeconds();

                Map<String,Object> fieldsToUpdate = getFieldForSetToEnd(endTime, durationSeconds);
                transaction.update(documentReference, fieldsToUpdate);
                log.info("Task [" + collectionName + "/" + documentId + "] updated");

                return true;
            }).get();
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to update task: " + ex.getMessage());
            return false;
        }
    }

    private Map<String,Object> getFieldForSetToEnd(Instant endTime, long durationSeconds) {
        Map<String,Object> result = new HashMap<>();
        result.put("status", TimedTask.STATUS_COMPLETED);
        result.put("endTime", Date.from(endTime));
        result.put("endTimeMillis", endTime.toEpochMilli());
        result.put("durationSecond", durationSeconds);
        return result;
    }

    @Override
    public boolean setToPending(String collectionName, String documentId) {
        try {
            final DocumentReference documentReference = firestore.collection(collectionName).document(documentId);
            return firestore.runTransaction(transaction -> {
                FSTimedTask task = transaction.get(documentReference).get().toObject(FSTimedTask.class);
                if ( task == null ) {
                    log.error("Invalid document: " + collectionName + "/" + documentId);
                    return false;
                }

                Map<String,Object> fieldsToUpdate = getFieldForSetToPending();
                transaction.update(documentReference, fieldsToUpdate);
                log.info("Task [" + collectionName + "/" + documentId + "] updated");

                return true;
            }).get();
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to update task: " + ex.getMessage());
            return false;
        }
    }

    private Map<String,Object> getFieldForSetToPending() {
        Map<String,Object> result = new HashMap<>();
        result.put("status", TimedTask.STATUS_PENDING);
        result.put("startTime", null);
        result.put("startTimeMillis", null);
        result.put("endTime", null);
        result.put("endTimeMillis", null);
        result.put("durationSecond", null);
        return result;
    }

}
