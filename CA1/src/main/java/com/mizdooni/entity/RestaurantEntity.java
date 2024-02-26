package com.mizdooni.entity;

import java.time.LocalTime;

public class RestaurantEntity {

    private int id;

    private String name;

    private int managerUserId;

    private String type;

    private String description;

    private LocalTime startTime;

    private LocalTime endTime;

    private int restaurantAddressId;

    public RestaurantEntity setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() { return this.id; }

    public RestaurantEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() { return this.name; }

    public RestaurantEntity setManagerUserId(int managerUserId) {
        this.managerUserId = managerUserId;
        return this;
    }

    public int getManagerUserId() { return this.managerUserId; }

    public RestaurantEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() { return this.type; }

    public RestaurantEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() { return this.description; }

    public RestaurantEntity setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LocalTime getStartTime() { return this.startTime; }

    public RestaurantEntity setEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public LocalTime getEndTime() { return this.endTime; }

    public RestaurantEntity setRestaurantAddressId(int restaurantAddressId) {
        this.restaurantAddressId = restaurantAddressId;
        return this;
    }

    public int getRestaurantAddressId() { return this.restaurantAddressId; }

}
