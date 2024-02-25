package com.mizdooni.entity;

public class AddressEntity {

    private int id;

    private String country;

    private String city;

    public int getId() { return this.id; }

    public AddressEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getCountry() { return this.country; }

    public AddressEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() { return this.city; }

    public AddressEntity setCity(String city) {
        this.city = city;
        return this;
    }
}
