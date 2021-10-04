package com.commercehub.link.qualifier;

import javax.enterprise.util.AnnotationLiteral;

public class LinkQualifierLiteral extends AnnotationLiteral<LinkQualifier> implements LinkQualifier {

    private final String clientName;

    public LinkQualifierLiteral(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String value() {
        return clientName;
    }

}
