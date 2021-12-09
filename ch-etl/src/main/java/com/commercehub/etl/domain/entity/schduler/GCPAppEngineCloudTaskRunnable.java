package com.commercehub.etl.domain.entity.schduler;

import com.commercehub.configuration.GCPConfigurations;
import com.commercehub.etl.common.ETLUtils;
import com.commercehub.etl.domain.entity.linking.Linking;
import com.google.cloud.storage.Storage;
import com.google.cloud.tasks.v2.*;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public abstract class GCPAppEngineCloudTaskRunnable implements TimedTaskRunnable {

    @Inject
    Logger log;

    @Inject
    Storage storage;

    @Inject
    GCPConfigurations configurations;

    @Override
    public boolean run(Linking linking, TimedTask task, String baseUri) {
        try (CloudTasksClient client = CloudTasksClient.create()) {
            // Rely on cloud storage settings
            String projectId = configurations.getProjectId();
            String location = storage.get(ETLUtils.BUCKET).getLocation();
            String finalLocation = location.toLowerCase();

            String queuePath = QueueName.of(projectId, finalLocation, queueName()).toString();

            String fullUri = getFullUri(linking, task, baseUri);
            HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                    .setUrl(fullUri)
                    .setHttpMethod(HttpMethod.POST);

            log.info("Full uri: " + fullUri);

            final String serviceAccountEmail = configurations.getServiceAccountEmail();
            if ( serviceAccountEmail != null && !serviceAccountEmail.isEmpty() ) {
                OidcToken oidcToken = OidcToken.newBuilder().setServiceAccountEmail(serviceAccountEmail).build();
                httpRequestBuilder.setOidcToken(oidcToken);
            }

            Task.Builder taskBuilder = Task.newBuilder().setHttpRequest(httpRequestBuilder.build());

//            Task.Builder taskBuilder =
//                    Task.newBuilder()
//                            .setAppEngineHttpRequest(
//                                    AppEngineHttpRequest.newBuilder()
//                                            .setRelativeUri(getRelativeUri(linking,task))
//                                            .setHttpMethod(HttpMethod.POST)
//                                            .build()
//                            );

            Task cloudTask = client.createTask(queuePath, taskBuilder.build());
            log.info("Task created: " + cloudTask.getName());
            return true;
        } catch(IOException ioe) {
            log.error("Failed to submit task to cloud task: " + ioe.getMessage());
        }

        return false;
    }

    private String getFullUri(Linking linking, TimedTask task, String baseUri) {
        String finalBaseUri = "";

        final String serviceAccountEmail = configurations.getServiceAccountEmail();
        if ( serviceAccountEmail != null && !serviceAccountEmail.isEmpty() ) {
            finalBaseUri = UriBuilder.fromUri(baseUri).scheme("https").build().toString();
        } else {
            finalBaseUri = baseUri;
        }

        String url = url();
        String finalUrl = url.startsWith("/") ? url.substring(1) : url;
        return finalBaseUri + finalUrl + "?" + getParams(linking, task);
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
