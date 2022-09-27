package com.gcp.cloud.logging;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

import java.util.logging.Level;

@ConfigRoot(phase = ConfigPhase.RUN_TIME, name = "log.gcp")
public class LoggingConfig {

    /**
     * Determine whether to enable the gcp logging extension.
     */
    @ConfigItem(defaultValue = "false")
    boolean enable;

    /**
     * The gcp log level.
     */
    @ConfigItem(defaultValue = "INFO")
    public Level level;

    /**
     * The gcp log flush level
     */
    @ConfigItem(defaultValue = "SEVERE")
    public Level flushLevel;

    @Override
    public String toString() {
        return "LoggingConfig{" +
                "enable=" + enable +
                ", level=" + level +
                ", flushLevel=" + flushLevel +
                '}';
    }
}
