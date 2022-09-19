package com.commercehub.etl.detail.usecase.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import com.commercehub.etl.core.usecase.scheduler.TimedTaskRunnable;
import com.commercehub.gcp.core.entity.ProjectConfiguration;
import com.commercehub.gcp.core.usecase.GetCloudTaskHttpRequestBuilder;
import com.commercehub.gcp.core.usecase.GetCloudTaskUrl;
import com.commercehub.gcp.core.usecase.GetProjectConfiguration;
import com.google.cloud.tasks.v2.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

@Dependent
public abstract class TimedTaskRunnableGCPCloudTask implements TimedTaskRunnable {

    @Inject
    Logger log;

    @Inject
    GetProjectConfiguration getProjectConfiguration;

    @Inject
    GetCloudTaskUrl getCloudTaskUrl;

    @Inject
    GetCloudTaskHttpRequestBuilder getCloudTaskHttpRequestBuilder;

    // Submit to task queue
    @Override
    public boolean run(Linking linking, TimedTask task) {
        try (CloudTasksClient client = CloudTasksClient.create()) {
            ProjectConfiguration configuration = getProjectConfiguration.execute();
            String queuePath = QueueName.of(configuration.projectId, queueLocation(), queueName()).toString();

            Map<String,Object> params = parameters(linking, task);
            String fullUri = getCloudTaskUrl.execute(baseUri(), path(), params);
            log.debug("Full uri: " + fullUri);

            HttpRequest.Builder httpRequestBuilder =
                    getCloudTaskHttpRequestBuilder.execute()
                            .setUrl(fullUri)
                            .setHttpMethod(HttpMethod.POST);

            Task.Builder taskBuilder =
                    Task.newBuilder()
                            .setHttpRequest(httpRequestBuilder.build());

            Task cloudTask = client.createTask(queuePath, taskBuilder.build());
            log.info("Successfully submit task to cloud task");

            return true;
        } catch(IOException ioe) {
            log.error("Failed to submit task to cloud task: " + ioe.getMessage());
        }

        return false;
    }

    public abstract String baseUri();
    public abstract String queueName();
    public abstract String queueLocation();
    public abstract String path();
    public abstract Map<String,Object> parameters(Linking linking, TimedTask task);

}
