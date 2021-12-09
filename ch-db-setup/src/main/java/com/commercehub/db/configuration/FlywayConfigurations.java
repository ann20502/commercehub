package com.commercehub.db.configuration;

import com.commercehub.configuration.GCPConfigurations;
import com.google.cloud.ServiceOptions;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class FlywayConfigurations {

    @Inject
    GCPConfigurations configurations;

    public String getUrl(String defaultDataset) {
        return hasConfiguredCredential() ?
                getUrlWithConfiguredCredential(defaultDataset)
                : getUrlWithDefaultCredential(defaultDataset);
    }

    private boolean hasConfiguredCredential() {
        final String projectId = configurations.getProjectId();
        final String serviceAccountLocation = configurations.getServiceAccountLocation();
        final String serviceAccountEmail = configurations.getServiceAccountEmail();

        return projectId != null && !projectId.isEmpty()
                && serviceAccountLocation != null && !serviceAccountLocation.isEmpty()
                && serviceAccountEmail != null && !serviceAccountEmail.isEmpty();
    }

    private String getUrlWithConfiguredCredential(String defaultDataset) {
        final String projectId = configurations.getProjectId();
        final String serviceAccountEmail = configurations.getServiceAccountEmail();
        final String pathToServiceAccount = configurations.getServiceAccountLocation();
        final int TIME_OUT_IN_SECOND = 60;

        return "jdbc:bigquery://https://www.googleapis.com/bigquery/v2:443;"
                + "ProjectId=" + projectId + ";"
                + "OAuthType=0;"
                + "OAuthServiceAcctEmail=" + serviceAccountEmail + ";"
                + "OAuthPvtKeyPath=" + pathToServiceAccount + ";"
                + "DefaultDataset=" + defaultDataset + ";"
                + "Timeout=" + TIME_OUT_IN_SECOND;
    }

    private String getUrlWithDefaultCredential(String defaultDataset) {
        final int TIME_OUT_IN_SECOND = 60;
        final String projectId = ServiceOptions.getDefaultProjectId();
        return new StringBuilder("jdbc:bigquery://https://www.googleapis.com/bigquery/v2:443;")
                .append("ProjectId=" + projectId + ";")
                .append("OAuthType=3;")
                .append("DefaultDataset=" + defaultDataset + ";")
                .append("Timeout=" + TIME_OUT_IN_SECOND)
                .toString();
    }

}
