package com.commercehub.etl.detail.usecaseinteractor.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.repository.TimedTaskRepository;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskGroupNameProvider;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskNameProviderShopeeOrderUpdate;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskSimulator;

import javax.enterprise.context.Dependent;

@Dependent
public class TimedTaskCreatorShopeeOrderUpdate extends TimedTaskCreatorBasedOnBusinessStartDateAndLatestTask {

    private final TimedTaskNameProviderShopeeOrderUpdate nameProviderShopeeOrderUpdate;
    private final TimedTaskRepository timedTaskRepository;

    public TimedTaskCreatorShopeeOrderUpdate(TimedTaskGroupNameProvider groupNameProvider, TimedTaskSimulator timedTaskSimulator, TimedTaskNameProviderShopeeOrderUpdate nameProviderShopeeOrderUpdate, TimedTaskRepository timedTaskRepository) {
        super(groupNameProvider, timedTaskSimulator);
        this.nameProviderShopeeOrderUpdate = nameProviderShopeeOrderUpdate;
        this.timedTaskRepository = timedTaskRepository;
    }

    @Override
    public boolean shallRun(Linking linking) {
        return true;
    }

    @Override
    public String name() {
        return nameProviderShopeeOrderUpdate.provide();
    }

    @Override
    public int minutePerTask() {
        return 30;
    }

    @Override
    public int maxTaskToCreateAtATime() {
        return 50;
    }

    @Override
    public int noOfDayInAdvance() {
        return 2;
    }

    @Override
    public TimedTaskRepository repository() {
        return timedTaskRepository;
    }

}
