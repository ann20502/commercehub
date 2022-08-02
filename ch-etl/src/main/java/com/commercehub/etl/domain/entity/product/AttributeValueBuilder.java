package com.commercehub.etl.domain.entity.product;

public class AttributeValueBuilder {
    private long attributeValueId;
    private String originalValueName;
    private String valueUnit;

    public AttributeValueBuilder setAttributeValueId(long attributeValueId) {
        this.attributeValueId = attributeValueId;
        return this;
    }

    public AttributeValueBuilder setOriginalValueName(String originalValueName) {
        this.originalValueName = originalValueName;
        return this;
    }

    public AttributeValueBuilder setValueUnit(String valueUnit) {
        this.valueUnit = valueUnit;
        return this;
    }

    public Item.AttributeValue createAttributeValue() {
        return new Item.AttributeValue(attributeValueId, originalValueName, valueUnit);
    }
}