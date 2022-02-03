package com.commercehub.etl.domain.repository;

import com.commercehub.etl.domain.entity.schduler.TimedTask;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSTimedTaskRepository implements TimedTaskRepository {

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public TimedTask get(String collectionName, String documentId) {
        try {
            final DocumentReference documentReference = firestore.collection(collectionName).document(documentId);
            DocumentSnapshot snapshot = documentReference.get().get();
            TimedTask timedTask = snapshot.toObject(TimedTask.class);
            if (timedTask != null) { timedTask.setId(snapshot.getId()); }
            return timedTask;
        } catch( InterruptedException | ExecutionException ex ) {
            throw new RuntimeException("Failed to retrieve task: " + ex.getMessage());
        }
    }

    @Override
    public TimedTask getLatest(String collectionName, String platform, String shopId) {
        try {
            final Query query = firestore.collection(collectionName)
                    .whereEqualTo("platform", platform)
                    .whereEqualTo("shopId", shopId)
                    .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                    .limit(1);

            return query
                    .get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        TimedTask task = snapshot.toObject(TimedTask.class);
                        task.setId(snapshot.getId());
                        return task;
                    })
                    .findFirst()
                    .orElse(null);
        } catch( InterruptedException | ExecutionException ex ) {
            throw new RuntimeException("Failed to retrieve latest task: " + ex.getMessage());
        }
    }

    @Override
    public List<TimedTask> getAll(String collectionName, String platform, String shopId, String status) {
        try {
            final Query query = firestore.collection(collectionName)
                    .whereEqualTo("platform", platform)
                    .whereEqualTo("shopId", shopId)
                    .whereEqualTo("status", status)
                    .orderBy("paramTimeFrom", Query.Direction.ASCENDING)
                    .limit(20);

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
            throw new RuntimeException("Failed to retrieve timed task: " + ex.getMessage());
        }
    }

    @Override
    public List<TimedTask> getAll(String collectionName, String platform, String shopId, String status, Instant maxTime, int noOfTaskToRun) {
        try {
            Date finalMaxTime = Date.from(maxTime);

            final Query query = firestore.collection(collectionName)
                    .whereEqualTo("platform", platform)
                    .whereEqualTo("shopId", shopId)
                    .whereEqualTo("status", status)
                    .whereLessThanOrEqualTo("paramTimeTo", finalMaxTime)
                    .orderBy("paramTimeTo", Query.Direction.ASCENDING)
                    .limit(noOfTaskToRun);

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
            throw new RuntimeException("Failed to retrieve timed task: " + ex.getMessage());
        }
    }

    @Override
    public boolean verifyLatestAndCreate(TimedTask latestTask, String collectionName, TimedTask... tasks) {
        try {
            final Query query = getQueryToVerifyLatest(latestTask, collectionName);
            final CollectionReference collectionReference = firestore.collection(collectionName);

            return firestore.runTransaction(transaction -> {
                if ( query != null ) {
                    String latestId = latestTask.getId();
                    List<QueryDocumentSnapshot> queryDocumentSnapshots = transaction.get(query).get().getDocuments();
                    if ( queryDocumentSnapshots.isEmpty() || !latestId.equals( queryDocumentSnapshots.get(0).getId() ) ) {
                        log.error("Task id " + latestId + " is no longer the latest, request reject!");
                        return false;
                    }
                }

                for (TimedTask task : tasks) {
                    Map<String,Object> additionalTaskField = getAdditionalTaskField(task);
                    DocumentReference documentReference = collectionReference.document();
                    transaction
                            .set(documentReference, task)
                            .update(documentReference, additionalTaskField);
                }

                log.info("[" + tasks.length + "] tasks added to [" + collectionName + "]");
                return true;
            }).get();
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to save tasks: " + ex.getMessage());
            throw new RuntimeException("Failed to save tasks: " + ex.getMessage());
        }
    }

    private Query getQueryToVerifyLatest(TimedTask latestTask, String collectionName) {
        if ( latestTask != null && latestTask.getId() != null ) {
            return firestore.collection(collectionName)
                    .whereEqualTo("shopId", latestTask.getShopId())
                    .orderBy("paramTimeFrom", Query.Direction.DESCENDING)
                    .limit(1);
        }
        return null;
    }

    private Map<String,Object> getAdditionalTaskField(TimedTask task) {
        Map<String,Object> result = new HashMap<>();
        result.put("paramTimeFromMillis", task.getParamTimeFrom().getTime());
        result.put("paramTimeToMillis", task.getParamTimeTo().getTime());
        return result;
    }

    @Override
    public boolean updateToStart(String collectionName, String documentId, String status, Date startTime) {
        try {
            Map<String,Object> fieldsToUpdate = getFieldsForUpdateToStart(status, startTime);
            Timestamp timestamp = firestore.collection(collectionName)
                    .document(documentId)
                    .update(fieldsToUpdate)
                    .get().getUpdateTime();
            log.info("Task [" + documentId + "] updated at: " + timestamp);
            return true;
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to save task: " + ex.getMessage());
            throw new RuntimeException("Failed to save tasks: " + ex.getMessage());
        }
    }

    private Map<String,Object> getFieldsForUpdateToStart(String status, Date startTime) {
        Map<String,Object> result = new HashMap<>();
        result.put("status", status);
        result.put("startTime", startTime);
        result.put("startTimeMillis", startTime.getTime());
        return result;
    }

    @Override
    public boolean updateToEnd(String collectionName, String documentId, String status, Date endTime) {
        try {
            final DocumentReference documentReference = firestore.collection(collectionName).document(documentId);
            return firestore.runTransaction(transaction -> {
                TimedTask task = transaction.get(documentReference).get().toObject(TimedTask.class);
                if ( task == null ) { throw new RuntimeException("Invalid document: " + collectionName + "/" + documentId); }

                long startTimeMillis = task.getStartTime().getTime();
                long endTimeMillis = endTime.getTime();
                Duration duration = Duration.ofMillis(endTimeMillis - startTimeMillis);
                long durationSeconds = duration.toSeconds();

                Map<String,Object> fieldsToUpdate = getFieldsForUpdateToEnd(status, endTime, durationSeconds);
                transaction.update(documentReference, fieldsToUpdate);
                log.info("Task [" + collectionName + "/" + documentId + "] updated");

                return true;
            }).get();
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to update task: " + ex.getMessage());
            throw new RuntimeException("Failed to update tasks: " + ex.getMessage());
        }
    }

    private Map<String,Object> getFieldsForUpdateToEnd(String status, Date endTime, long duration) {
        Map<String,Object> result = new HashMap<>();
        result.put("status", status);
        result.put("endTime", endTime);
        result.put("endTimeMillis", endTime.getTime());
        result.put("durationSecond", duration);
        return result;
    }

    @Override
    public boolean revertToPending(String collectionName, String documentId) {
        try {
            final DocumentReference documentReference = firestore.collection(collectionName).document(documentId);
            return firestore.runTransaction(transaction -> {
                TimedTask task = transaction.get(documentReference).get().toObject(TimedTask.class);
                if ( task == null ) { throw new RuntimeException("Invalid document: " + collectionName + "/" + documentId); }

                Map<String,Object> fieldsToUpdate = getFieldsForRevertToPending();
                transaction.update(documentReference, fieldsToUpdate);
                log.info("Task [" + collectionName + "/" + documentId + "] updated");

                return true;
            }).get();
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to update task: " + ex.getMessage());
            throw new RuntimeException("Failed to update tasks: " + ex.getMessage());
        }
    }

    private Map<String,Object> getFieldsForRevertToPending() {
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
