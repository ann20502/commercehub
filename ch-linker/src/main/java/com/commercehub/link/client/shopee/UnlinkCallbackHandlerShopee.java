package com.commercehub.link.client.shopee;

import com.commercehub.link.client.UnlinkCallbackHandler;
import com.commercehub.link.client.repository.Linking;
import com.commercehub.link.client.repository.LinkingRepository;
import com.commercehub.link.exception.LinkRuntimeException;
import com.commercehub.link.qualifier.LinkQualifier;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

@Dependent
@LinkQualifier("shopee")
public class UnlinkCallbackHandlerShopee implements UnlinkCallbackHandler {

    @Inject
    Logger log;

    @Inject
    LinkingRepository repository;

    @Override
    public Uni<Boolean> handle(String documentId, MultivaluedMap<String, String> reqParam) {
        return Uni.createFrom().item(documentId)
                .map(id -> {
                    String shopId = getShopId(reqParam);
                    Linking linking = repository.get(id);
                    if ( !shopId.equals(linking.getShopId()) ) {
                        log.warn("Shop ID given by Shopee Callback does not match with user selected shop ID !!");
                        Linking actualLinking = repository.get(linking.getPlatform(), linking.getPartnerId(), shopId);
                        if ( actualLinking == null ) {
                            throw new LinkRuntimeException(
                                    "Unknown linking "
                                    + linking.getPlatform()
                                    + " - "
                                    + linking.getPartnerId()
                                    + " - "
                                    + linking.getShopId()
                            );
                        }

                        log.warn("Replace user selected linking with the actual linking being disabled !!");
                        return actualLinking.getId();
                    }
                    return id;
                })
                .map(repository::disable);
    }

    private String getShopId(MultivaluedMap<String,String> reqParam) {
        return reqParam.getFirst("shop_id");
    }

}
