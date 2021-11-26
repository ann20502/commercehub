package com.commercehub.linker.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationConfiguration {

    @ConfigProperty(name = "quarkus.http.root-path")
    String rootPath;

    public String rootPath() {
        return rootPath;
    }

}
