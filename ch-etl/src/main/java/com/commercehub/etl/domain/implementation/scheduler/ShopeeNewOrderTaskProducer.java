package com.commercehub.etl.domain.implementation.scheduler;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.schduler.TimedTaskProducer;
import com.commercehub.etl.domain.repository.TimedTaskRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ShopeeNewOrderTaskProducer extends TimedTaskProducer {

    @Inject
    TimedTaskRepository repository;

    // TODO: Based on fire store query result
    @Override
    public boolean shallRun(Linking linking) {
        return true;
    }

    @Override
    public String taskName() {
        return "ShopeeNewOrder";
    }

    @Override
    public TimedTaskRepository repository() {
        return repository;
    }

}
