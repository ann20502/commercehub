package com.commercehub.etl.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;

@Dependent
public class FlywayConfiguration implements SchemaMigrationConfiguration {

    @ConfigProperty(name = "quarkus.google.cloud.project-id")
    String projectId;

    @ConfigProperty(name = "commercehub.google.cloud.service-account-name")
    String serviceAccountName;

    @ConfigProperty(name = "quarkus.google.cloud.service-account-location")
    String pathToServiceAccount;

    @Override
    public String projectId() {
        return projectId;
    }

    @Override
    public String serviceAccountName() {
        return serviceAccountName;
    }

    @Override
    public String pathToServiceAccount() {
        return pathToServiceAccount;
    }

    public String getUrl() {
        return new StringBuilder("jdbc:bigquery://https://www.googleapis.com/bigquery/v2:443;")
                .append("ProjectId=" + projectId + ";")
                .append("OAuthType=0;")
                .append("OAuthServiceAcctEmail=" + serviceAccountName + ";")
                .append("OAuthPvtKeyPath=" + pathToServiceAccount + ";")
                .toString();
    }

    public String getUrl(String defaultDataset) {
        final int TIME_OUT_IN_SECOND = 60;
        return new StringBuilder("jdbc:bigquery://https://www.googleapis.com/bigquery/v2:443;")
                .append("ProjectId=" + projectId + ";")
                .append("OAuthType=0;")
                .append("OAuthServiceAcctEmail=" + serviceAccountName + ";")
                .append("OAuthPvtKeyPath=" + pathToServiceAccount + ";")
                .append("DefaultDataset=" + defaultDataset + ";")
                .append("Timeout=" + TIME_OUT_IN_SECOND)
                .toString();
    }

}
