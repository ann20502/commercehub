package com.commercehub.rest;

import com.commercehub.rest.output.ExtractOrderOutput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@RegisterRestClient(configKey = "etl-api")
public interface ETLService {

    @GET
    @Path("/order/extract")
    Uni<ExtractOrderOutput> extractOrder(@QueryParam("collectionName") String collectionName, @QueryParam("documentId") String documentId);

}
