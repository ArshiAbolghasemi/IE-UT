package com.mizdooni.lib.dto.request.user;

import com.fasterxml.jackson.annotation.JsonSetter;

public class AddReviewDTO {

    private String username;

    private String restaurantName;

    private int foodRate;

    private int serviceRate;

    private int ambianceRate;

    private int overallRate;

    private String comment;

    @JsonSetter("username")
    private void setUsername(String username) { this.username = username; }

    public String getUsername() { return username; }

    @JsonSetter("restaurantName")
    private void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    private String getRestaurantName() { return this.restaurantName; }

    @JsonSetter("foodRate")
    private void setFoodRate(int foodRate) { this.foodRate = foodRate; }

    public int getFoodRate() { return foodRate; }

    @JsonSetter("serviceRate")
    private void setServiceRate(int serviceRate) { this.serviceRate = serviceRate; }

    private int getServiceRate() { return this.serviceRate; }

    @JsonSetter("ambianceRate")
    private void setAmbianceRate(int ambianceRate) { this.ambianceRate = ambianceRate; }

    private int getAmbianceRate() { return this.ambianceRate; }

    @JsonSetter("overallRate")
    private void setOverallRate(int overallRate) { this.overallRate = overallRate; }

    public int getOverallRate() { return overallRate; }

    @JsonSetter("comment")
    private void setComment(String comment) { this.comment = comment; }

    private String getComment() { return this.comment; }

}
