package com.commercehub.gcp.detail.usecase;

import com.commercehub.gcp.core.entity.ProjectConfiguration;
import com.commercehub.gcp.core.usecase.GetCloudTaskHttpRequestBuilder;
import com.commercehub.gcp.core.usecase.GetProjectConfiguration;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.profile.UnlessBuildProfile;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Produces;

@Dependent
public class GetCloudTaskHttpRequestBuilderProducer {

    @Inject
    GetProjectConfiguration getProjectConfiguration;

    @ApplicationScoped
    @Produces
    @UnlessBuildProfile("dev")
    public GetCloudTaskHttpRequestBuilder prod() {
        ProjectConfiguration configuration = getProjectConfiguration.execute();
        return new GetCloudTaskHttpRequestBuilderProd(configuration.serviceAccountEmail);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    public GetCloudTaskHttpRequestBuilder dev() {
        return new GetCloudTaskHttpRequestBuilderDev();
    }

}
