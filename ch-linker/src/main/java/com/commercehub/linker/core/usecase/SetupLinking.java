package com.commercehub.linker.core.usecase;

import io.smallrye.mutiny.Uni;

import java.util.Map;

public interface SetupLinking {

    Uni<Boolean> setup(String id, Map<String,Object> fieldToSetup);

}
