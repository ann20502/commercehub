package com.commercehub.linker.core.entity;

public class ModuleConfiguration {

    public final String rootPath;

    public ModuleConfiguration(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String toString() {
        return "ModuleConfiguration{" +
                "rootPath='" + rootPath + '\'' +
                '}';
    }
}
