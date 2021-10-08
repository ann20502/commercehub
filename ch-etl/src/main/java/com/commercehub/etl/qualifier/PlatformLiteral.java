package com.commercehub.etl.qualifier;

import javax.enterprise.util.AnnotationLiteral;

public class PlatformLiteral extends AnnotationLiteral<Platform> implements Platform {

    private final String platform;

    public PlatformLiteral(String platform) {
        this.platform = platform;
    }

    @Override
    public String value() {
        return platform;
    }

}
