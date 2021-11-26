package com.commercehub.etl.domain.usecase.scheduler;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.schduler.ShopIdentifier;
import com.commercehub.etl.domain.entity.schduler.TimedTask;
import com.commercehub.etl.domain.entity.schduler.TimedTaskProducer;
import com.commercehub.etl.domain.repository.LinkingRepository;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class DefineTimedTask {

    @Inject
    LinkingRepository linkingRepository;

    @Inject
    @Any
    Instance<TimedTaskProducer> producers;

    public Multi<List<TimedTask>> execute() {
        return Multi.createFrom()
                .iterable(linkingRepository.getAll(Linking.STATUS_ACTIVE, true, true))
                .collect().asMap(linking -> new ShopIdentifier(linking.getPlatform(), linking.getShopId()))
                .toMulti()
                .flatMap(map -> Multi.createFrom().iterable(map.values()))
                .flatMap(linking ->
                        Multi.createFrom().iterable(producers)
                                .map(producer -> producer.produce(linking))
                );
    }

}
