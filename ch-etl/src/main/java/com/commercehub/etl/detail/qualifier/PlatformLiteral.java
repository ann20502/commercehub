package com.commercehub.etl.detail.qualifier;

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
