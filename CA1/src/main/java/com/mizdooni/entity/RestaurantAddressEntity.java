package com.mizdooni.entity;

public class RestaurantAddressEntity extends AddressEntity {

    private String street;

    public RestaurantAddressEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getStreet() { return this.street; }

}
