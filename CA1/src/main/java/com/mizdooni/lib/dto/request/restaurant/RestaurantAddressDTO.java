package com.mizdooni.lib.dto.request.restaurant;

import com.mizdooni.lib.dto.request.AddressDTO;

public class RestaurantAddressDTO extends AddressDTO {

    private String street;

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() { return this.street; }

}
