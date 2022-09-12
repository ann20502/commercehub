package com.commercehub.gcp.detail.usecase;

import com.commercehub.gcp.core.entity.ProjectConfiguration;
import com.commercehub.gcp.core.usecase.GetProjectConfiguration;
import com.google.cloud.ServiceOptions;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import java.util.Optional;

@Dependent
public class GetProjectConfigurationInteractor implements GetProjectConfiguration {

    @ConfigProperty(name = "quarkus.google.cloud.project-id")
    Optional<String> projectId;

    @ConfigProperty(name = "quarkus.google.cloud.service-account-location")
    Optional<String> serviceAccountLocation;

    @ConfigProperty(name = "quarkus.google.cloud.service-account-email")
    Optional<String> serviceAccountEmail;

    @Override
    public ProjectConfiguration execute() {
        return new ProjectConfiguration(
                projectId.orElseGet(ServiceOptions::getDefaultProjectId),
                serviceAccountLocation.orElse(""),
                serviceAccountEmail.orElse("")
        );
    }

}
