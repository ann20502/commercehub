package com.commercehub.etl.core.usecase.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import io.smallrye.mutiny.Uni;

public interface TimedTaskCreator {

    Uni<Boolean> create(Linking linking);

    String name();

}
