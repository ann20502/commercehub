package com.commercehub.etl.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;

@Dependent
public class CloudTaskConfiguration {

    @ConfigProperty(name = "quarkus.google.cloud.project-id")
    String projectId;

    @ConfigProperty(name = "commercehub.google.cloud.location")
    String location;

    public String projectId() {
        return projectId;
    }

    public String location() {
        return location;
    }

}
