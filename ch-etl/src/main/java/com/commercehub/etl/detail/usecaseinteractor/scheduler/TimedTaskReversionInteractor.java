package com.commercehub.etl.detail.usecaseinteractor.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.repository.TimedTaskRepository;
import com.commercehub.etl.core.usecase.linking.GetOneLinkPerShop;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskCreator;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskGroupNameProvider;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskReversion;
import com.google.cloud.Tuple;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class TimedTaskReversionInteractor implements TimedTaskReversion {

    @Inject
    GetOneLinkPerShop getOneLinkPerShop;

    @Inject
    Instance<TimedTaskCreator> creators;

    @Inject
    TimedTaskGroupNameProvider groupNameProvider;

    @Inject
    TimedTaskRepository repository;

    @Override
    public Multi<Boolean> revertError() {
        return getOneLinkPerShop.execute()
                .flatMap(this::getCollectionNameAndTaskToRevert)
                .map(collectionNameAndTask -> this.revertError(
                        collectionNameAndTask.x(),
                        collectionNameAndTask.y()
                ));
    }

    private Multi<Tuple<String,List<TimedTask>>> getCollectionNameAndTaskToRevert(Linking linking) {
        return Multi.createFrom()
                .iterable(creators)
                .map(creator -> groupNameProvider.provide(creator.name()))
                .map(collectionName -> Tuple.of(
                        collectionName,
                        repository.getAll(
                                collectionName,
                                linking.platform,
                                linking.shopId,
                                TimedTask.STATUS_ERROR
                        )
                ));
    }

    private boolean revertError(String collectionName, List<TimedTask> tasks) {
        boolean result = true;
        for ( TimedTask task : tasks ) {
            if ( !repository.setToPending(collectionName, task.id) ) {
                result = false;
                break;
            }
        }
        return result;
    }

}
