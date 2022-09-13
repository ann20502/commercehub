package com.commercehub.gcp.detail.usecase;

import com.commercehub.gcp.core.entity.ProjectConfiguration;
import com.commercehub.gcp.core.usecase.GetHttpScheme;
import com.commercehub.gcp.core.usecase.GetProjectConfiguration;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class GetHttpSchemeInteractor implements GetHttpScheme {

    @Inject
    GetProjectConfiguration getProjectConfiguration;

    @Override
    public String execute() {
        ProjectConfiguration configuration = getProjectConfiguration.execute();
        return configuration.serviceAccountEmail.isEmpty() ? "http" : "https";
    }

}
