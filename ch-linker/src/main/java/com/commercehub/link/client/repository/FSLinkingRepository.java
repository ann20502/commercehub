package com.commercehub.link.client.repository;

import com.commercehub.link.exception.LinkRuntimeException;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.StreamSupport;

@Dependent
public class FSLinkingRepository implements LinkingRepository {

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public Linking get(String id) {
        try {
            DocumentSnapshot snapshot = firestore.collection("linking").document(id).get().get();
            Linking result = snapshot.toObject(Linking.class);
            if ( result != null ) { result.setId(snapshot.getId()); }
            return result;
        } catch(InterruptedException | ExecutionException ex) {
            throw new LinkRuntimeException("Failed to retrieve linking: " + ex.getMessage());
        }
    }

    @Override
    public Linking get(String platform, String partnerId, String shopId) {
        try {
            String documentId = getLinkingDocumentId(platform, partnerId, shopId);
            DocumentSnapshot snapshot =  firestore.collection("linking").document(documentId).get().get();
            Linking result = snapshot.toObject(Linking.class);
            if ( result != null ) { result.setId(snapshot.getId()); }
            return result;
        } catch(InterruptedException | ExecutionException ex) {
            throw new LinkRuntimeException("Failed to retrieve linking: " + ex.getMessage());
        }
    }

    @Override
    public boolean insertOrUpdateIfExist(Linking linking) {
        try {
            String documentId = getLinkingDocumentId(
                    linking.getPlatform(),
                    linking.getPartnerId(),
                    linking.getShopId()
            );

            Timestamp updateTime = firestore.collection("linking").document(documentId)
                    .set(linking).get().getUpdateTime();

            log.info("Document " + documentId + " created/updated at " + updateTime.toString());
            return true;
        } catch (InterruptedException | ExecutionException ex) {
            log.error("Failed to insert/update linking: " + ex.getMessage());
        }

        return false;
    }

    private String getLinkingDocumentId(String platform, String partnerId, String shopId) {
        return platform + "-" + partnerId + "-" + shopId;
    }

    @Override
    public boolean disable(String documentId) {
        try {
            Timestamp timestamp = firestore.collection("linking").document(documentId)
                    .update("link", false).get().getUpdateTime();

            log.info("Linking " + documentId + " disabled at " + timestamp);
            return true;
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to disable linking: " + ex.getMessage());
        }

        return false;
    }

}
