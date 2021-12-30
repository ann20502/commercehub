package com.commercehub.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CloudStorageConfigurations {

    @ConfigProperty(name = "commercehub.google.cloud.storage.bucket")
    String bucket;

    public String bucket() {
        return bucket;
    }

}
