package com.gcp.cloud.logging;

import com.google.cloud.logging.LoggingHandler;
import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;

import java.util.Optional;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

@Recorder
public class LoggingHandlerFactory {

    public RuntimeValue<Optional<Handler>> create(final LoggingConfig config) {
        if ( !config.enable ) {
            return new RuntimeValue<>(Optional.empty());
        }

        LoggingHandler handler = new LoggingHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(config.level);
        handler.setFlushLevel(config.flushLevel);
        return new RuntimeValue<>(Optional.of(handler));
    }

}
