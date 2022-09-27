package com.gcp.cloud.logging.deployment;

import com.gcp.cloud.logging.LoggingConfig;
import com.gcp.cloud.logging.LoggingHandlerFactory;
import com.google.cloud.logging.LoggingHandler;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.ExtensionSslNativeSupportBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.LogHandlerBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

class GcpCloudLoggingProcessor {

    private static final String FEATURE = "gcp-cloud-logging";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    LogHandlerBuildItem addGCPCloudLoggingHandler(
            final LoggingConfig config,
            final LoggingHandlerFactory factory
    ) {
        return new LogHandlerBuildItem(factory.create(config));
    }

    @BuildStep
    ExtensionSslNativeSupportBuildItem activateSslNativeSupport() {
        return new ExtensionSslNativeSupportBuildItem(FEATURE);
    }

    @BuildStep
    ReflectiveClassBuildItem addReflection() {
        return new ReflectiveClassBuildItem(
                true,
                true,
                LoggingHandler.class
        );
    }

}
