package com.commercehub.linker.detail.usecase;

import com.commercehub.linker.core.entity.ModuleConfiguration;
import com.commercehub.linker.core.usecase.GetModuleConfiguration;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import java.util.Optional;

@Dependent
public class GetModuleConfigurationInteractor implements GetModuleConfiguration {

    @ConfigProperty(name = "quarkus.http.root-path")
    Optional<String> rootPath;

    @Override
    public ModuleConfiguration execute() {
        return new ModuleConfiguration(rootPath.orElse(""));
    }

}
