package com.commercehub.etl.detail.repository.product.builder;

import com.commercehub.etl.detail.repository.product.BQItemShopee;

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

    public BQItemShopee.AttributeValue createAttributeValue() {
        return new BQItemShopee.AttributeValue(attributeValueId, originalValueName, valueUnit);
    }
}