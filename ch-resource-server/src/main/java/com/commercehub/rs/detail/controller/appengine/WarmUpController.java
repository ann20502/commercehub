package com.commercehub.rs.detail.controller.appengine;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/_ah/warmup")
public class WarmUpController {

    /**
     * To fulfill Google App Engine's warm up request
     *
     * @return return "ok"
     */
    @GET
    public Uni<String> execute() {
        return Uni.createFrom().item("ok");
    }

}
