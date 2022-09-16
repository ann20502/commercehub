package com.commercehub.rs.detail.openapi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Commerce Hub Resource Server API",
                version = "0.1.0"
        )
)
@SecuritySchemes(
        value = {
                @SecurityScheme(
                        securitySchemeName = "JWT",
                        type = SecuritySchemeType.HTTP,
                        in = SecuritySchemeIn.HEADER,
                        scheme = "Bearer",
                        bearerFormat = "JWT"
                )
        }
)
public class ApiDefinition extends Application {}
