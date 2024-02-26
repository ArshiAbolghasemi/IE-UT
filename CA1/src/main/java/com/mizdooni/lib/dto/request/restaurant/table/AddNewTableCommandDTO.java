package com.mizdooni.lib.dto.request.restaurant.table;

import com.fasterxml.jackson.annotation.JsonSetter;

public class AddNewTableCommandDTO {

    private int tableNumber;

    private String restaurantName;

    private String managerUsername;

    private int seatsNumber;

    @JsonSetter("tableNumber")
    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }

    public int getTableNumber() { return this.tableNumber; }

    @JsonSetter("restaurantName")
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getRestaurantName() { return this.restaurantName; }

    @JsonSetter("managerUsername")
    public void setManagerUsername(String managerUsername) { this.managerUsername = managerUsername; }

    public String getManagerUsername() { return this.managerUsername; }

    @JsonSetter("seatsNumber")
    public void setSeatsNumber(int seatsNumber) { this.seatsNumber = seatsNumber; }

    public int getSeatsNumber() { return this.seatsNumber; }

}
