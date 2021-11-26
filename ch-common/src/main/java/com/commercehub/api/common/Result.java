package com.commercehub.api.common;

public class Result {

    public final Object result;
    public final String error;

    public Result(Object result, String error) {
        this.result = result;
        this.error = error;
    }

}
