package com.commercehub.link.client.shopee;

import com.commercehub.link.client.UnlinkCallbackHandler;
import com.commercehub.link.client.persistence.Linking;
import com.commercehub.link.exception.LinkRuntimeException;
import com.commercehub.link.qualifier.LinkQualifier;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;
import java.util.concurrent.ExecutionException;

@Dependent
@LinkQualifier("shopee")
public class UnlinkCallbackHandlerShopee implements UnlinkCallbackHandler {

    @Inject
    Firestore firestore;

    @Override
    public Uni<Boolean> handle(MultivaluedMap<String, String> reqParam) {
        return Uni.createFrom()
                .item(getShopId(reqParam))
                .map(this::disablePlatform);
    }

    private String getShopId(MultivaluedMap<String,String> reqParam) {
        return reqParam.getFirst("shop_id");
    }

    private boolean disablePlatform(String shopId) {
        try {
            final Query query = firestore.collection("linking").whereEqualTo("shopId", shopId);
            return firestore.runTransaction(transaction -> {
                for (QueryDocumentSnapshot snapshot : query.get().get().getDocuments() ) {
                    DocumentReference documentReference = snapshot.getReference();
                    transaction.update(documentReference, "status", Linking.STATUS_INACTIVE);
                }
                return true;
            }).get();
        } catch(InterruptedException | ExecutionException ex) {
            System.out.println("Failed to disable platform: " + ex.getMessage());
            throw new LinkRuntimeException("Failed to disable platform: " + ex.getMessage());
        }
    }

}
