package com.commercehub.db.detail.repository;

import com.commercehub.db.core.entity.Linking;
import com.commercehub.db.core.repository.LinkingRepository;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.WriteResult;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSLinkingRepository implements LinkingRepository {

    private final String COLLECTION_NAME = "linking";

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public List<Linking> getAll(String linkingStatus, boolean hasSetup, boolean hasLink) {
        final Query query = firestore.collection(COLLECTION_NAME)
                .whereEqualTo("status", linkingStatus)
                .whereEqualTo("setup", hasSetup)
                .whereEqualTo("link", hasLink);
        return executeQueryAndReturnParsedResult(query);
    }

    private List<Linking> executeQueryAndReturnParsedResult(Query query) {
        try {
            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> snapshot.toObject(FSLinking.class))
                    .map(LinkingTransformer::from)
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }

        return Collections.emptyList();
    }

    @Override
    public boolean recordSetup(String documentId, boolean setupResult) {
        try {
            WriteResult result = firestore.collection(COLLECTION_NAME).document(documentId)
                    .update("setup", setupResult)
                    .get();

            log.info("Setup recorded at " + result.getUpdateTime());
            return true;
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to record setup: " + ex.getMessage());
        }

        return false;
    }

}
