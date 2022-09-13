package com.commercehub.db.detail.usecase;

import com.commercehub.db.core.usecase.GetFlywayUrl;
import com.commercehub.gcp.core.entity.ProjectConfiguration;
import com.commercehub.gcp.core.usecase.GetProjectConfiguration;
import com.google.cloud.ServiceOptions;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GetFlywayUrlInteractor implements GetFlywayUrl {

    @Inject
    GetProjectConfiguration getProjectConfiguration;

    @Override
    public String execute(String dataset) {
        ProjectConfiguration configuration = getProjectConfiguration.execute();
        return hasConfiguredCredential(configuration) ?
                getUrlWithConfiguredCredential(configuration, dataset)
                : getUrlWithDefaultCredential(dataset);
    }

    private boolean hasConfiguredCredential(ProjectConfiguration configuration) {
        final String projectId = configuration.projectId;
        final String serviceAccountLocation = configuration.serviceAccountLocation;
        final String serviceAccountEmail = configuration.serviceAccountEmail;

        return projectId != null && !projectId.isEmpty()
                && serviceAccountLocation != null && !serviceAccountLocation.isEmpty()
                && serviceAccountEmail != null && !serviceAccountEmail.isEmpty();
    }

    private String getUrlWithConfiguredCredential(ProjectConfiguration configuration, String dataset) {
        final String projectId = configuration.projectId;
        final String serviceAccountEmail = configuration.serviceAccountEmail;
        final String pathToServiceAccount = configuration.serviceAccountLocation;
        final int TIME_OUT_IN_SECOND = 60;

        return "jdbc:bigquery://https://www.googleapis.com/bigquery/v2:443;"
                + "ProjectId=" + projectId + ";"
                + "OAuthType=0;"
                + "OAuthServiceAcctEmail=" + serviceAccountEmail + ";"
                + "OAuthPvtKeyPath=" + pathToServiceAccount + ";"
                + "DefaultDataset=" + dataset + ";"
                + "Timeout=" + TIME_OUT_IN_SECOND;
    }

    private String getUrlWithDefaultCredential(String dataset) {
        final int TIME_OUT_IN_SECOND = 60;
        final String projectId = ServiceOptions.getDefaultProjectId();
        return new StringBuilder("jdbc:bigquery://https://www.googleapis.com/bigquery/v2:443;")
                .append("ProjectId=" + projectId + ";")
                .append("OAuthType=3;")
                .append("DefaultDataset=" + dataset + ";")
                .append("Timeout=" + TIME_OUT_IN_SECOND)
                .toString();
    }

}
