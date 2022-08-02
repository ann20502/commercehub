package com.commercehub.etl.implementation.repository;

import com.commercehub.etl.domain.entity.product.Boost;
import com.commercehub.etl.domain.repository.BoostRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Dependent
public class FSBoostRepository implements BoostRepository {

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public Boost getSetting(String platform, String shopId) {
        try {
            final String documentId = getDocumentId(platform, shopId);
            DocumentSnapshot snapshot = firestore.collection("boost").document(documentId).get().get();
            if ( snapshot.exists() ) { return new Boost(snapshot.getId(), snapshot.getData()); }
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve boost setting: " + ex.getMessage());
        }

        log.error("Boost Setting returning null");
        return null;
    }

    @Override
    public boolean saveSuccessAttempt(String platform, String shopId, List<Long> itemIds) {
        try {
            final String documentId = getDocumentId(platform, shopId);
            DocumentReference documentReference = firestore.collection("boost").document(documentId);

            firestore.runTransaction(transaction -> {
                ApiFuture<DocumentSnapshot> snapshot = transaction.get(documentReference);
                DocumentSnapshot documentSnapshot = snapshot.get();
                Map<String,Object> fieldToUpdate = new HashMap<>();
                for ( Long id : itemIds ) {
                    String strId = id.toString();
                    Long currentCount = documentSnapshot.getLong(id.toString());
                    currentCount = currentCount == null ? 1 : currentCount + 1L;
                    fieldToUpdate.put(strId, currentCount);
                }
                transaction.update(documentReference, fieldToUpdate);
                return null;
            }).get();

            return true;
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to save success attempt: " + ex.getMessage() );
        }

        return false;
    }

    private String getDocumentId(String platform, String shopId) {
        return platform + "-" + shopId;
    }
}
