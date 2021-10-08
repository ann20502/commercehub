package com.commercehub.etl.domain.repository;

import com.commercehub.etl.domain.entity.Linking;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSLinkingRepository implements LinkingRepository {

    @Inject
    Firestore firestore;

    @Override
    public List<Linking> getAll(String platformStatus) {
        return null;
    }

    @Override
    public List<Linking> getAllWithTokenExpired(String platformStatus, int fastForwardMinute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, fastForwardMinute);
        Date currentTime = calendar.getTime();

        try {
            final Query query = firestore.collection("linking")
                    .whereEqualTo("status", platformStatus)
                    .whereLessThanOrEqualTo("accessTokenExpiry", currentTime);

            return query
                    .get().get().getDocuments()
                    .stream()
                    .map(snapshot -> snapshot.toObject(Linking.class))
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            System.out.println("Failed to retrieve linking: " + ex.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public boolean updateToken(Linking linking) {
        final Map<String,Object> fieldToUpdate = getTokenFieldToUpdate(linking);
        final Query query = firestore.collection("linking")
                .whereEqualTo("platform", linking.getPlatform())
                .whereEqualTo("shopId", linking.getShopId());

        try {
            for ( QueryDocumentSnapshot snapshot : query.get().get().getDocuments() ) {
                snapshot.getReference().update(fieldToUpdate).get();
            }
            return true;
        } catch( InterruptedException | ExecutionException ex ) {
            System.out.println("Failed to save token: " + ex.getMessage());
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
