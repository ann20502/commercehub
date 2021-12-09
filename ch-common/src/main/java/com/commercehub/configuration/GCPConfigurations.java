package com.commercehub.configuration;

import com.google.cloud.ServiceOptions;
import io.smallrye.config.ConfigMapping;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class GCPConfigurations {

    @ConfigMapping(prefix = "quarkus.google.cloud.project-id")
    Optional<String> projectId;

    @ConfigMapping(prefix = "quarkus.google.cloud.service-account-location")
    Optional<String> serviceAccountLocation;

    @ConfigMapping(prefix = "quarkus.google.cloud.service-account-email")
    Optional<String> serviceAccountEmail;

    public String getProjectId() {
        return projectId.orElseGet(ServiceOptions::getDefaultProjectId);
    }

    public String getServiceAccountLocation() {
        return serviceAccountLocation.orElse(null);
    }

    public String getServiceAccountEmail() {
        return serviceAccountEmail.orElse(null);
    }

}
