package com.commercehub.etl.core.usecase.scheduler;

import io.smallrye.mutiny.Multi;

public interface TimedTaskReversion {

    Multi<Boolean> revertError();

}
