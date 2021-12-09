package com.commercehub.etl.domain.entity.schduler;

import com.commercehub.etl.domain.entity.linking.Linking;

public interface TimedTaskRunnable {

    public boolean run(Linking linking, TimedTask task, String baseUri);

}
