package com.commercehub.linker.domain.repository;

import com.commercehub.linker.domain.entity.Linking;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSLinkingRepository implements LinkingRepository {

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public List<Linking> getAll(boolean link) {
        try {
            final Query query = firestore.collection("linking").whereEqualTo("link", link);
            return query
                    .get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        Linking linking = snapshot.toObject(Linking.class);
                        linking.setId(snapshot.getId());
                        return linking;
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public List<Linking> getAll(String status) {
        try {
            return firestore.collection("linking")
                    .whereEqualTo("status", status)
                    .get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        Linking linking = snapshot.toObject(Linking.class);
                        linking.setId(snapshot.getId());
                        return linking;
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public Linking get(String documentId) {
        try {
            DocumentSnapshot snapshot = firestore.collection("linking").document(documentId).get().get();
            Linking linking = snapshot.toObject(Linking.class);
            if ( linking != null ) { linking.setId(snapshot.getId()); }
            return linking;
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(String documentId, Map<String, Object> values) {
        try {
            final DocumentReference reference = firestore.collection("linking").document(documentId);
            return firestore.runTransaction(transaction -> {
                transaction
                    .update(reference, values);
//                    .update(reference, "setup", true);
                return true;
            }).get();
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to insert/update linking: " + ex.getMessage());
        }
        return false;
    }

}
