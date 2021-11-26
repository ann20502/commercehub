package com.commercehub.api.common;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

public class Streams {

    private static final Logger log = Logger.getLogger(Streams.class);

    public static <T> Uni<Result> toApiResult(Uni<T> stream) {
        return stream
                .map(input -> new Result(input, ""))
                .onFailure().recoverWithItem(
                        error -> {
                            log.error("Api error: " + error.getMessage());
                            error.printStackTrace();
                            return new Result(null, error.getMessage());
                        }
                );
    }

    public static <T> Uni<Result> toApiResult(Multi<T> stream) {
        return stream.collect().asList()
                .map(input -> new Result(input, ""))
                .onFailure().recoverWithItem(
                        error -> {
                            log.error("Api error: " + error.getMessage());
                            error.printStackTrace();
                            return new Result(null, error.getMessage());
                        }
                );
    }

}
