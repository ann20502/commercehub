package com.commercehub.etl.detail.usecase.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.repository.TimedTaskRepository;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskRunnable;
import com.commercehub.gcp.core.usecase.GetHttpScheme;

import javax.enterprise.context.Dependent;
import javax.ws.rs.core.UriInfo;
import java.time.Duration;

@Dependent
public class TimedTaskExecutorShopeeOrderUpdate extends TimedTaskExecutorWithDelay {

    private final GetHttpScheme getHttpScheme;
    private final TimedTaskRepository repository;
    private final TimedTaskRunnableShopeeOrderUpdate runnable;

    public TimedTaskExecutorShopeeOrderUpdate(GetHttpScheme getHttpScheme, TimedTaskRepository repository, TimedTaskRunnableShopeeOrderUpdate runnable, UriInfo uriInfo) {
        this.getHttpScheme = getHttpScheme;
        this.repository = repository;
        this.runnable = runnable;
        this.runnable.setBaseUri(uriInfo.getBaseUriBuilder().scheme(getHttpScheme.execute()).build().toString());
    }

    @Override
    public boolean shallRun(Linking linking) {
        return true;
    }

    @Override
    public TimedTaskRunnable runnable() {
        return runnable;
    }

    @Override
    public String name() {
        return "ShopeeOrderUpdate";
    }

    @Override
    public TimedTaskRepository repository() {
        return repository;
    }

    @Override
    public Duration buffer() {
        return Duration.ofMinutes(30);
    }
}
