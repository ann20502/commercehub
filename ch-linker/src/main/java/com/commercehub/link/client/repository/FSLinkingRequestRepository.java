package com.commercehub.link.client.repository;

import com.commercehub.link.exception.LinkRuntimeException;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.concurrent.ExecutionException;

@Dependent
public class FSLinkingRequestRepository implements LinkingRequestRepository {

    @Inject
    Logger log;

    @Inject
    Firestore firestore;

    @Override
    public LinkingRequest add(LinkingRequest linkingRequest) {
        try {
            String documentId = firestore.collection("linkingRequest")
                    .add(linkingRequest)
                    .get().get().get().getId();

            LinkingRequest result = new LinkingRequest(linkingRequest);
            result.setId(documentId);
            return result;
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to save linking request: " + ex.getMessage());
            throw new LinkRuntimeException("Failed to save linking request: " + ex.getMessage());
        }
    }

    @Override
    public LinkingRequest get(String id) {
        try {
            DocumentSnapshot snapshot = firestore.collection("linkingRequest").document(id).get().get();
            LinkingRequest result = snapshot.toObject(LinkingRequest.class);
            if ( result != null ) { result.setId(snapshot.getId()); }
            return result;
        } catch(InterruptedException | ExecutionException ex) {
            log.error("Failed to save linking request: " + ex.getMessage());
            throw new LinkRuntimeException("Failed to save linking request: " + ex.getMessage());
        }
    }

}
