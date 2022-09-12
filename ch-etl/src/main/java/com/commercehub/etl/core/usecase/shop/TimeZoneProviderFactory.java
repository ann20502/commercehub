package com.commercehub.etl.core.usecase.shop;

public interface TimeZoneProviderFactory {

    TimeZoneProvider dispatch(String platform);

}
