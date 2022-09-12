package com.commercehub.etl.detail.usecaseinteractor.scheduler;

import com.commercehub.etl.core.usecase.linking.GetOneLinkPerShop;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskCreation;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskCreator;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Dependent
public class TimedTaskCreationInteractor implements TimedTaskCreation {

    @Inject
    GetOneLinkPerShop getOneLinkPerShop;

    @Inject
    Instance<TimedTaskCreator> timedTaskCreators;

    @Override
    public Multi<Boolean> create() {
        return getOneLinkPerShop.execute()
                .flatMap(linking ->
                        Multi.createFrom().iterable(timedTaskCreators)
                                .flatMap(creator -> creator.create(linking).toMulti())
                );
    }

}
