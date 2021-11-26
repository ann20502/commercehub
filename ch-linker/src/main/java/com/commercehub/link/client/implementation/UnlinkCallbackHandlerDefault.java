package com.commercehub.link.client.implementation;

import com.commercehub.link.client.UnlinkCallbackHandler;
import com.commercehub.link.qualifier.LinkDefault;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.ws.rs.core.MultivaluedMap;

@Dependent
@LinkDefault
public class UnlinkCallbackHandlerDefault implements UnlinkCallbackHandler {

    @Override
    public Uni<Boolean> handle(String documentId, MultivaluedMap<String, String> reqParam) {
        return Uni.createFrom().item(true);
    }

}
