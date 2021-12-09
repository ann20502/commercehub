package com.commercehub.db.domain.repository;

import com.commercehub.db.domain.entity.Linking;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.WriteResult;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSLinkingRepository implements LinkingRepository {

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public List<Linking> getAll(String linkingStatus, boolean hasSetup, boolean hasLink) {
        try {
            final Query query = firestore.collection("linking")
                    .whereEqualTo("status", linkingStatus)
                    .whereEqualTo("setup", hasSetup)
                    .whereEqualTo("link", hasLink);

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
    public boolean updateSetup(String documentId, boolean setupResult) {
        try {
            WriteResult result = firestore.collection("linking").document(documentId)
                    .update("setup", setupResult)
                    .get();

            log.info("Linking updated at " + result.getUpdateTime());
            return true;
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to update linking: " + ex.getMessage());
        }

        return false;
    }

}
