package com.commercehub.linker.vm;

import com.commercehub.linker.domain.usecase.GetAvailablePlatformName;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class PlatformViewModel {

    @Inject
    GetAvailablePlatformName getAvailablePlatformName;

    public Multi<String> getAll() {
        return getAvailablePlatformName.getAll();
    }

}
