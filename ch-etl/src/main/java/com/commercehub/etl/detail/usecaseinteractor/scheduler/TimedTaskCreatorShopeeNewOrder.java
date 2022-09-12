package com.commercehub.etl.detail.usecaseinteractor.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.repository.TimedTaskRepository;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskGroupNameProvider;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskNameProviderShopeeNewOrder;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskSimulator;

import javax.enterprise.context.Dependent;

@Dependent
public class TimedTaskCreatorShopeeNewOrder extends TimedTaskCreatorBasedOnBusinessStartDateAndLatestTask {

    private final TimedTaskNameProviderShopeeNewOrder nameProviderShopeeNewOrder;
    private final TimedTaskRepository timedTaskRepository;

    public TimedTaskCreatorShopeeNewOrder(TimedTaskGroupNameProvider groupNameProvider, TimedTaskSimulator timedTaskSimulator, TimedTaskNameProviderShopeeNewOrder nameProviderShopeeNewOrder, TimedTaskRepository timedTaskRepository) {
        super(groupNameProvider, timedTaskSimulator);
        this.nameProviderShopeeNewOrder = nameProviderShopeeNewOrder;
        this.timedTaskRepository = timedTaskRepository;
    }

    @Override
    public boolean shallRun(Linking linking) {
        return true;
    }

    @Override
    public String name() {
        return nameProviderShopeeNewOrder.provide();
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
