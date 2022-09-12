package com.commercehub.etl.detail.repository.linking;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.repository.LinkingRepository;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.WriteResult;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
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
    public List<Linking> getFirst(String platform, String shopId, boolean hasSetup, boolean hasLink) {
        Query query = firestore.collection(COLLECTION_NAME)
                .whereEqualTo("platform", platform)
                .whereEqualTo("shopId", shopId)
                .whereEqualTo("setup", hasSetup)
                .whereEqualTo("link", hasLink);

        List<Linking> result = executeQueryAndReturnParsedResult(query);
        return result.isEmpty() ? Collections.emptyList() : result.subList(0,1);
    }

    @Override
    public List<Linking> getAll(String linkingStatus) {
        Query query = firestore.collection(COLLECTION_NAME).whereEqualTo("status", linkingStatus);
        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<Linking> getAll(String linkingStatus, boolean hasSetup) {
        Query query = firestore.collection("linking")
                .whereEqualTo("status", linkingStatus)
                .whereEqualTo("setup", hasSetup);

        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<Linking> getAll(String linkingStatus, boolean hasSetup, boolean hasLink) {
        Query query = firestore.collection("linking")
                .whereEqualTo("status", linkingStatus)
                .whereEqualTo("setup", hasSetup)
                .whereEqualTo("link", hasLink);

        return executeQueryAndReturnParsedResult(query);
    }

    @Override
    public List<Linking> getAllWithTokenExpired(String linkingStatus, int fastForwardMinute) {
        Duration FAST_FORWARD_MINUTES = Duration.ofMinutes(fastForwardMinute);
        Instant now = Instant.now();
        Instant forwardedMinutes = now.plus(FAST_FORWARD_MINUTES);
        Date targetTime = Date.from(forwardedMinutes);

        Query query = firestore.collection("linking")
                .whereEqualTo("status", linkingStatus)
                .whereEqualTo("link", true)
                .whereLessThanOrEqualTo("accessTokenExpiry", targetTime);

        return executeQueryAndReturnParsedResult(query);
    }

    private List<Linking> executeQueryAndReturnParsedResult(Query query) {
        try {
            return query.get().get().getDocuments()
                    .stream()
                    .map(snapshot -> snapshot.toObject(FSLinking.class))
                    .map(LinkingTransformer::transform)
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }

        return Collections.emptyList();
    }

    @Override
    public boolean updateToken(String documentId, String accessToken, Instant accessTokenExpiry, String refreshToken) {
        final Map<String,Object> fieldToUpdate = packFieldToUpdate(accessToken, accessTokenExpiry, refreshToken);
        final DocumentReference documentReference = firestore.collection("linking").document(documentId);

        try {
            WriteResult writeResult = documentReference.update(fieldToUpdate).get();
            log.info("Token updated for linking [" + documentId + "] at " + writeResult.getUpdateTime());
            return true;
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to update token: " + ex.getMessage());
        }

        return false;
    }

    private Map<String,Object> packFieldToUpdate(String accessToken, Instant accessTokenExpiry, String refreshToken) {
        Map<String,Object> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("accessTokenExpiry", Date.from(accessTokenExpiry));
        result.put("refreshToken", refreshToken);
        return result;
    }

}
