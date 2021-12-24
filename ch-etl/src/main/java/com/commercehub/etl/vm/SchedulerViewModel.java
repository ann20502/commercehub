package com.commercehub.etl.vm;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.schduler.ShopIdentifier;
import com.commercehub.etl.domain.entity.schduler.TimedTask;
import com.commercehub.etl.domain.usecase.linking.GetLinking;
import com.commercehub.etl.domain.usecase.scheduler.DefineTimedTask;
import com.commercehub.etl.domain.usecase.scheduler.ExecuteTimedTask;
import com.commercehub.etl.domain.usecase.scheduler.RevertTimedTask;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class SchedulerViewModel {

    @Inject
    DefineTimedTask defineTimedTask;

    @Inject
    ExecuteTimedTask executeTimedTask;

    @Inject
    GetLinking getLinking;

    @Inject
    RevertTimedTask revertTimedTask;

    public Multi<List<TimedTask>> defineTimedTaskTrigger() {
        return defineTimedTask.execute();
    }

    public Multi<List<TimedTask>> executeTimedTaskTrigger() {
        return executeTimedTask.execute();
    }

    public Multi<TimedTask> revertErrorTimeTask() {
        return getLinking.getAll(Linking.STATUS_ACTIVE, true, true)
                .onItem().transformToMulti(linkings -> Multi.createFrom().iterable(linkings))
                .collect().asMap(linking -> new ShopIdentifier(linking.getPlatform(), linking.getShopId()))
                .toMulti()
                .flatMap(map -> Multi.createFrom().iterable(map.values()))
                .flatMap(linking -> revertTimedTask.revert(linking));
    }

}
