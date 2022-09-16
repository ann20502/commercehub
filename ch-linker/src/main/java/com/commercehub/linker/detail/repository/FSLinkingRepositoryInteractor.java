package com.commercehub.linker.detail.repository;

import com.commercehub.linker.core.entity.Linking;
import com.commercehub.linker.core.repository.LinkingRepository;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSLinkingRepositoryInteractor implements LinkingRepository {

    private final String COLLECTION_LINKING = "linking";

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public List<Linking> getJustOne(String documentId) {
        try {
            DocumentSnapshot snapshot = firestore.collection(COLLECTION_LINKING).document(documentId).get().get();
            FSLinking linking = snapshot.toObject(FSLinking.class);
            if ( linking != null ) { return List.of(LinkingTransformer.from(linking)); }
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }

        return Collections.emptyList();
    }

    @Override
    public List<Linking> getAll(boolean isLinked) {
        final Query query = firestore.collection(COLLECTION_LINKING)
                .whereEqualTo("link", isLinked);

        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<Linking> getAll(String status) {
        final Query query =  firestore.collection(COLLECTION_LINKING)
                .whereEqualTo("status", status);

        return executeQueryAndReturnParsedResult(query);
    }

    private List<Linking> executeQueryAndReturnParsedResult(Query query) {
        try {
            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> snapshot.toObject(FSLinking.class))
                    .map(LinkingTransformer::from)
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException ex) {
            log.error("Failed to retrieve timed task: " + ex.getMessage());
        }

        return Collections.emptyList();
    }

    @Override
    public boolean update(String documentId, Map<String, Object> fieldToUpdate) {
        try {
            final DocumentReference reference = firestore.collection(COLLECTION_LINKING).document(documentId);
            return firestore.runTransaction(transaction -> {
                transaction.update(reference, fieldToUpdate);
                return true;
            }).get();
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to update linking: " + ex.getMessage());
        }

        return false;
    }

}
