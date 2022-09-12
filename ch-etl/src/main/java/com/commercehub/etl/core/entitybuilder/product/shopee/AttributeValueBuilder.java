package com.commercehub.etl.core.entitybuilder.product.shopee;

import com.commercehub.etl.core.entity.product.ItemShopee;

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

    public ItemShopee.AttributeValue createAttributeValue() {
        return new ItemShopee.AttributeValue(attributeValueId, originalValueName, valueUnit);
    }
}