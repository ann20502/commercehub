package com.commercehub.etl.core.usecase.order;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import io.smallrye.mutiny.Uni;

public interface OrderETLWorker {

    Uni<Boolean> extractTransformLoad(Linking linking, TimedTask task);

    String taskName();

}
