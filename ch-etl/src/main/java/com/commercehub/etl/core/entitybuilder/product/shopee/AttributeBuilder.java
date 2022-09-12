package com.commercehub.etl.core.entitybuilder.product.shopee;

import com.commercehub.etl.core.entity.product.ItemShopee;

import java.util.List;

public class AttributeBuilder {
    private long attributeId;
    private String originalAttributeName;
    private boolean is_mandatory;
    private List<ItemShopee.AttributeValue> attributeValues;

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

    public AttributeBuilder setAttributeValues(List<ItemShopee.AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
        return this;
    }

    public ItemShopee.Attribute createAttribute() {
        return new ItemShopee.Attribute(attributeId, originalAttributeName, is_mandatory, attributeValues);
    }
}