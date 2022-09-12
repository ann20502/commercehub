package com.commercehub.etl.core.usecase.linking;

public interface AccessTokenRenewalWorkerFactory {

    AccessTokenRenewalWorker dispatch(String platform);

}
