package com.commercehub.etl.domain.entity.product;

import java.util.List;

public class AttributeBuilder {
    private long attributeId;
    private String originalAttributeName;
    private boolean is_mandatory;
    private List<Item.AttributeValue> attributeValues;

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

    public AttributeBuilder setAttributeValues(List<Item.AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
        return this;
    }

    public Item.Attribute createAttribute() {
        return new Item.Attribute(attributeId, originalAttributeName, is_mandatory, attributeValues);
    }
}