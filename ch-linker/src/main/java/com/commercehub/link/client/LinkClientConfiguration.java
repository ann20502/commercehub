package com.commercehub.link.client;

import io.smallrye.config.WithName;

import java.util.Optional;

public interface LinkClientConfiguration {

    @WithName("client-id")
    String clientId();

    @WithName("client-secret")
    String clientSecret();

    @WithName("redirect-path")
    String redirectPath();

    @WithName("api-base-path")
    String apiBasePath();

    @WithName("api-version-path")
    Optional<String> apiVersionPath();

    @WithName("unlink-redirect-path")
    String unlinkRedirectPath();

}
