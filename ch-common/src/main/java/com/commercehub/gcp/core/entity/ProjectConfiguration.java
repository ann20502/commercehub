package com.commercehub.gcp.core.entity;

public class ProjectConfiguration {

    public final String projectId;
    public final String serviceAccountLocation;
    public final String serviceAccountEmail;

    public ProjectConfiguration(String projectId, String serviceAccountLocation, String serviceAccountEmail) {
        this.projectId = projectId;
        this.serviceAccountLocation = serviceAccountLocation;
        this.serviceAccountEmail = serviceAccountEmail;
    }

    @Override
    public String toString() {
        return "ProjectConfiguration{" +
                "projectId='" + projectId + '\'' +
                ", serviceAccountLocation='" + serviceAccountLocation + '\'' +
                ", serviceAccountEmail='" + serviceAccountEmail + '\'' +
                '}';
    }

}
