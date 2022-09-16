package com.commercehub.linker.core.usecase;

import io.smallrye.mutiny.Multi;

public interface GetSupportedPlatform {

    Multi<String> execute();

}
