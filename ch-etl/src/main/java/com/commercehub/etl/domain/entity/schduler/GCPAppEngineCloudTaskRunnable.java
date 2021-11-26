package com.commercehub.etl.domain.entity.schduler;

import com.commercehub.etl.configuration.CloudTaskConfiguration;
import com.commercehub.etl.domain.entity.linking.Linking;
import com.google.cloud.tasks.v2.*;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

public abstract class GCPAppEngineCloudTaskRunnable implements TimedTaskRunnable {

    @Inject
    Logger log;

    @Inject
    CloudTaskConfiguration configuration;

    @Override
    public boolean run(Linking linking, TimedTask task) {
        try (CloudTasksClient client = CloudTasksClient.create()) {
            String queuePath = QueueName.of(configuration.projectId(), configuration.location(), queueName()).toString();

            Task.Builder taskBuilder =
                    Task.newBuilder()
                            .setAppEngineHttpRequest(
                                    AppEngineHttpRequest.newBuilder()
                                            .setRelativeUri(getRelativeUri(linking,task))
                                            .setHttpMethod(HttpMethod.POST)
                                            .build()
                            );

            Task cloudTask = client.createTask(queuePath, taskBuilder.build());
            log.info("Task created: " + cloudTask.getName());
            return true;
        } catch(IOException ioe) {
            log.error("Failed to submit task to cloud task: " + ioe.getMessage());
        }

        return false;
    }

    private String getRelativeUri(Linking linking, TimedTask task) {
        return url() + "?" + getParams(linking, task);
    }

    private String getParams(Linking linking, TimedTask task) {
        StringBuilder result = new StringBuilder();

        Map<String,Object> params = parameters(linking, task);
        if ( params != null ) {
            for ( Map.Entry<String,Object> entry : params.entrySet() ) {
                result.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }

        return result.toString();
    }

    public abstract String queueName();
    public abstract String url();
    public abstract Map<String,Object> parameters(Linking linking, TimedTask task);

}
