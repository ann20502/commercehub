package com.commercehub.gcp.detail.usecase;

import com.commercehub.gcp.core.entity.ProjectConfiguration;
import com.commercehub.gcp.core.usecase.GetCloudTaskHttpRequestBuilder;
import com.commercehub.gcp.core.usecase.GetProjectConfiguration;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.profile.IfBuildProfile;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Produces;

@Dependent
public class GetCloudTaskHttpRequestBuilderProducer {

    @Inject
    GetProjectConfiguration getProjectConfiguration;

    @Produces
    @DefaultBean
    public GetCloudTaskHttpRequestBuilder dev() {
        return new GetCloudTaskHttpRequestBuilderDev();
    }

    @Produces
    @IfBuildProfile("prod")
    public GetCloudTaskHttpRequestBuilder prod() {
        ProjectConfiguration configuration = getProjectConfiguration.execute();
        return new GetCloudTaskHttpRequestBuilderProd(configuration.serviceAccountEmail);
    }

}
