package com.commercehub.db.controller;

import com.commercehub.api.common.Result;
import com.commercehub.api.common.Streams;
import com.commercehub.db.domain.entity.Linking;
import com.commercehub.db.domain.repository.LinkingRepository;
import com.commercehub.db.domain.usecase.MigrateDatabase;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/database/create")
public class CreateDatabaseController {

    @Inject
    LinkingRepository repository;

    @Inject
    MigrateDatabase migrateDatabase;

    @Blocking
    @POST
    @Operation(summary = "Create database schema", description = "Create database for linking that has not been SETUP")
    public Uni<Result> execute() {
        Multi<Linking> stream = Multi
                .createFrom().iterable(repository.getAll(Linking.STATUS_ACTIVE, false, true))
                .filter(linking -> migrateDatabase.migrate(linking))
                .filter(linking -> repository.updateSetup(linking.getId(), true))
                .map(linking -> {
                    linking.setSetup(true);
                    return linking;
                });

        return Streams.toApiResult(stream);
    }

}
