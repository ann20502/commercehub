package com.commercehub.rs.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BigQueryConfiguration {

    @ConfigProperty(name = "quarkus.google.cloud.project-id")
    String projectId;

    public String projectId() {
        return projectId;
    }

}
