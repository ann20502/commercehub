package com.commercehub.etl.domain.repository;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.linking.LinkingBuilder;
import com.google.cloud.firestore.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSLinkingRepository implements LinkingRepository {

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public Linking get(String platform, String shopId, boolean hasSetup, boolean hasLink) {
        try {
            final Query query = firestore.collection("linking")
                    .whereEqualTo("platform", platform)
                    .whereEqualTo("shopId", shopId)
                    .whereEqualTo("setup", hasSetup)
                    .whereEqualTo("link", hasLink);

            return query
                    .get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        Linking linking = snapshot.toObject(Linking.class);
                        return new LinkingBuilder(linking)
                                .setId(snapshot.getId())
                                .createLinking();
                    })
                    .findFirst()
                    .orElse(null);
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }

        return null;
    }

    @Override
    public List<Linking> getAll(String linkingStatus) {
        try {
            final Query query = firestore.collection("linking")
                    .whereEqualTo("status", linkingStatus);

            return query
                    .get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        Linking linking = snapshot.toObject(Linking.class);
                        return new LinkingBuilder(linking)
                                .setId(snapshot.getId())
                                .createLinking();
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public List<Linking> getAll(String linkingStatus, boolean hasSetup) {
        try {
            final Query query = firestore.collection("linking")
                    .whereEqualTo("status", linkingStatus)
                    .whereEqualTo("setup", hasSetup);

            return query
                    .get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        Linking linking = snapshot.toObject(Linking.class);
                        return new LinkingBuilder(linking)
                                .setId(snapshot.getId())
                                .createLinking();
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }

        return new ArrayList<>();
    }

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
                        return new LinkingBuilder(linking)
                                .setId(snapshot.getId())
                                .createLinking();
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public List<Linking> getAllWithTokenExpired(String linkingStatus, int fastForwardMinute) {
        Duration THIRTY_MINUTES = Duration.ofMinutes(30);
        Instant now = Instant.now();
        Instant thirtyMinutesLater = now.plus(THIRTY_MINUTES);
        Date targetTime = Date.from(thirtyMinutesLater);

        try {
            final Query query = firestore.collection("linking")
                    .whereEqualTo("status", linkingStatus)
                    .whereEqualTo("link", true)
                    .whereLessThanOrEqualTo("accessTokenExpiry", targetTime);

            return query
                    .get().get().getDocuments()
                    .stream()
                    .map(snapshot -> {
                        Linking linking = snapshot.toObject(Linking.class);
                        return new LinkingBuilder(linking)
                                .setId(snapshot.getId())
                                .createLinking();
                    })
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to retrieve linking: " + ex.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public boolean updateToken(Linking linking) {
        final Map<String,Object> fieldToUpdate = getTokenFieldToUpdate(linking);
        final DocumentReference documentReference = firestore.collection("linking").document(linking.getId());

        try {
            WriteResult writeResult = documentReference.update(fieldToUpdate).get();
            log.info("Updated token for linking [" + linking.getId() + "] at" + writeResult.getUpdateTime());
            return true;
        } catch( InterruptedException | ExecutionException ex ) {
            log.error("Failed to update token: " + ex.getMessage());
        }

        return false;
    }

    private Map<String,Object> getTokenFieldToUpdate(Linking linking) {
        Map<String,Object> result = new HashMap<>();
        result.put("accessToken", linking.getAccessToken());
        result.put("accessTokenExpiry", linking.getAccessTokenExpiry());
        result.put("refreshToken", linking.getRefreshToken());
        return result;
    }

}
