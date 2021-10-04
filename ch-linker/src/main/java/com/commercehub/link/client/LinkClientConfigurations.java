package com.commercehub.link.client;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
@ConfigMapping(prefix = "com.commercehub.link")
public interface LinkClientConfigurations {

    @WithName("clients")
    Map<String, LinkClientConfiguration> clients();

}
