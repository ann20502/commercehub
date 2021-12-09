package com.commercehub.db.configuration;

import com.google.cloud.ServiceOptions;

import javax.enterprise.context.Dependent;

@Dependent
public class FlywayConfigurations {

    public String getUrlWithDefaultCredential(String defaultDataset) {
        final int TIME_OUT_IN_SECOND = 60;
        final String projectId = ServiceOptions.getDefaultProjectId();
        return new StringBuilder("jdbc:bigquery://https://www.googleapis.com/bigquery/v2:443;")
                .append("ProjectId=" + projectId + ";")
                .append("OAuthType=3;")
                .append("DefaultDataset=" + defaultDataset + ";")
                .append("Timeout=" + TIME_OUT_IN_SECOND)
                .toString();
    }

    // Just in case
    public String getUrlWithInjectedCredential(String defaultDataset) {
        final String projectId = "";
        final String serviceAccountName = "";
        final String pathToServiceAccount = "";
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
