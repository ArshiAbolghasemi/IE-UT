package com.mizdooni.lib.dto.request.restaurant;

import com.fasterxml.jackson.annotation.JsonSetter;

public class SearchRestaurantByTypeDTO {

    private String type;

    @JsonSetter("type")
    public void setType(String type) { this.type = type; }

    public String getType() { return this.type; }

}
