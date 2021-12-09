package com.commercehub.etl.domain.usecase.scheduler;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.schduler.ShopIdentifier;
import com.commercehub.etl.domain.entity.schduler.TimedTask;
import com.commercehub.etl.domain.entity.schduler.TimedTaskExecutor;
import com.commercehub.etl.domain.repository.LinkingRepository;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Dependent
public class ExecuteTimedTask {

    @Inject
    LinkingRepository linkingRepository;

    @Inject
    @Any
    Instance<TimedTaskExecutor> executors;

    @Inject
    UriInfo uriInfo;

    public Multi<List<TimedTask>> execute() {
        String baseUri = uriInfo.getBaseUri().toString();
        return Multi.createFrom()
                .iterable(linkingRepository.getAll(Linking.STATUS_ACTIVE, true, true))
                .collect().asMap(linking -> new ShopIdentifier(linking.getPlatform(), linking.getShopId()))
                .toMulti()
                .flatMap(map -> Multi.createFrom().iterable(map.values()))
                .flatMap(linking ->
                        Multi.createFrom().iterable(executors)
                                .flatMap(executor -> executor.execute(linking, baseUri).toMulti())
                );
    }

}
