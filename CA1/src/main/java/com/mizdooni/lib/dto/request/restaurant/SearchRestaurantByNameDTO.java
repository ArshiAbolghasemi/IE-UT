package com.mizdooni.lib.dto.request.restaurant;

import com.fasterxml.jackson.annotation.JsonSetter;

public class SearchRestaurantByNameDTO {

    private String name;

    @JsonSetter("name")
    public void  setName(String name) { this.name = name; }

    public String getName() { return this.name; }

}
