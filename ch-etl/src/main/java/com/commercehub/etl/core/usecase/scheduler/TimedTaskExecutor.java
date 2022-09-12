package com.commercehub.etl.core.usecase.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import io.smallrye.mutiny.Uni;

public interface TimedTaskExecutor {

    Uni<Boolean> execute(Linking linking);

    String name();

}
