package com.commercehub.linker.core.usecase.scheduler;

import io.smallrye.mutiny.Uni;

import java.util.List;

public interface GetTaskGroup {

    Uni<List<String>> getAll();

}
