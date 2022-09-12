package com.commercehub.etl.core.entitybuilder.order.shopee;

import com.commercehub.etl.core.entity.order.OrderShopee;

public class RecipientAddressBuilder {
    private String name;
    private String phone;
    private String town;
    private String district;
    private String city;
    private String state;
    private String region;
    private String zipcode;
    private String fullAddress;

    public RecipientAddressBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RecipientAddressBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public RecipientAddressBuilder setTown(String town) {
        this.town = town;
        return this;
    }

    public RecipientAddressBuilder setDistrict(String district) {
        this.district = district;
        return this;
    }

    public RecipientAddressBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public RecipientAddressBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public RecipientAddressBuilder setRegion(String region) {
        this.region = region;
        return this;
    }

    public RecipientAddressBuilder setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public RecipientAddressBuilder setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
        return this;
    }

    public OrderShopee.RecipientAddress createRecipientAddress() {
        return new OrderShopee.RecipientAddress(name, phone, town, district, city, state, region, zipcode, fullAddress);
    }
}