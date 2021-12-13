package com.commercehub.etl.domain.implementation.scheduler;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.schduler.TimedTaskExecutor;
import com.commercehub.etl.domain.entity.schduler.TimedTaskRunnable;
import com.commercehub.etl.domain.repository.TimedTaskRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ShopeeOrderUpdateTaskExecutor extends TimedTaskExecutor {

    @Inject
    ShopeeOrderUpdateTaskRunnable runnable;

    @Inject
    TimedTaskRepository repository;

    @Override
    public boolean shallRun(Linking linking) {
        return true;
    }

    @Override
    public TimedTaskRunnable runnable() {
        return runnable;
    }

    @Override
    public String taskName() {
        return "ShopeeOrderUpdate";
    }

    @Override
    public TimedTaskRepository repository() {
        return repository;
    }

}
