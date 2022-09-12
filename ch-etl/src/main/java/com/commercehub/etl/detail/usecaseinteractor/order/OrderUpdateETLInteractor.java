package com.commercehub.etl.detail.usecaseinteractor.order;

import com.commercehub.common.Utils;
import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.repository.LinkingRepository;
import com.commercehub.etl.core.repository.TimedTaskRepository;
import com.commercehub.etl.core.usecase.order.OrderUpdateETL;
import com.commercehub.etl.core.usecase.order.OrderUpdateETLWorkerFactory;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskGroupNameProvider;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class OrderUpdateETLInteractor implements OrderUpdateETL {

    @Inject
    OrderUpdateETLWorkerFactory factory;

    @Inject
    TimedTaskGroupNameProvider groupNameProvider;

    @Inject
    TimedTaskRepository repository;

    @Inject
    LinkingRepository linkingRepository;

    @Override
    public Uni<Boolean> extractTransformLoad(String documentId, String platform) {
        return Uni.createFrom().item(() -> factory.dispatch(platform))
                .flatMap(worker -> {
                    String collectionName = groupNameProvider.provide(worker.taskName());
                    List<TimedTask> tasks = repository.getSingle(collectionName, documentId);
                    if ( tasks.isEmpty() ) {
                        throw new IllegalArgumentException("Invalid document");
                    }

                    TimedTask task = tasks.get(Utils.POS_ZERO);
                    List<Linking> links = linkingRepository.getFirst(task.platform, task.shopId, true, true);
                    if ( links.isEmpty() ) {
                        throw new IllegalArgumentException("Unknown shop");
                    }

                    Linking linking = links.get(Utils.POS_ZERO);
                    return worker.extractTransformLoad(linking, task);
                });
    }

}
