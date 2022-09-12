package com.commercehub.gcp.detail.usecase;

import com.commercehub.gcp.core.entity.CloudStorageConfiguration;
import com.commercehub.gcp.core.usecase.GetCloudStorageConfiguration;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import java.util.Optional;

@Dependent
public class GetCloudStorageConfigurationInteractor implements GetCloudStorageConfiguration {

    @ConfigProperty(name = "commercehub.google.cloud.storage.bucket")
    Optional<String> bucketETL;

    @Override
    public CloudStorageConfiguration execute() {
        return new CloudStorageConfiguration(bucketETL.orElse(""));
    }

}
