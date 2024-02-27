package com.mizdooni.lib.dto.request.restaurant;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ShowAvailableTablesDTO {

    private String restaurantName;

    @JsonSetter("restaurantName")
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getRestaurantName() { return restaurantName; }

}
