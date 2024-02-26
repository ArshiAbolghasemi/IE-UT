package com.mizdooni.lib.dto.request.user;

public class AddressDTO {

    private String country;

    private String city;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() { return this.country; }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() { return this.city; }

}
