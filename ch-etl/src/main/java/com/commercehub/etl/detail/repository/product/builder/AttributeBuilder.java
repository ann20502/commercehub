package com.commercehub.etl.detail.repository.product.builder;

import com.commercehub.etl.detail.repository.product.BQItemShopee;

import java.util.List;

public class AttributeBuilder {
    private long attributeId;
    private String originalAttributeName;
    private boolean is_mandatory;
    private List<BQItemShopee.AttributeValue> attributeValues;

    public AttributeBuilder setAttributeId(long attributeId) {
        this.attributeId = attributeId;
        return this;
    }

    public AttributeBuilder setOriginalAttributeName(String originalAttributeName) {
        this.originalAttributeName = originalAttributeName;
        return this;
    }

    public AttributeBuilder setIs_mandatory(boolean is_mandatory) {
        this.is_mandatory = is_mandatory;
        return this;
    }

    public AttributeBuilder setAttributeValues(List<BQItemShopee.AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
        return this;
    }

    public BQItemShopee.Attribute createAttribute() {
        return new BQItemShopee.Attribute(attributeId, originalAttributeName, is_mandatory, attributeValues);
    }
}