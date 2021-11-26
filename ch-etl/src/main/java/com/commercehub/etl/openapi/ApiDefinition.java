package com.commercehub.etl.openapi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Commerce Hub ETL API",
                version = "0.1.0"
        )
)
//@SecuritySchemes(
//        value = {
//                @SecurityScheme(
//                        securitySchemeName = "JWT",
//                        type = SecuritySchemeType.HTTP,
//                        in = SecuritySchemeIn.HEADER,
//                        scheme = "Bearer",
//                        bearerFormat = "JWT"
//                )
//        }
//)
public class ApiDefinition {}
