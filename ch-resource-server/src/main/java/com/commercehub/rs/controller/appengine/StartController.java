package com.commercehub.rs.controller.appengine;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/_ah/start")
public class StartController {

    /**
     * To fulfill Google App Engine's start up request
     *
     * @return return "ok"
     */
    @GET
    public Uni<String> execute() {
        return Uni.createFrom().item("ok");
    }

}
