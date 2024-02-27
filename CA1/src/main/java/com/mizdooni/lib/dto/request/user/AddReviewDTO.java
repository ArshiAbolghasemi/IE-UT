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
    public void setUsername(String username) { this.username = username; }

    public String getUsername() { return username; }

    @JsonSetter("restaurantName")
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getRestaurantName() { return this.restaurantName; }

    @JsonSetter("foodRate")
    public void setFoodRate(int foodRate) { this.foodRate = foodRate; }

    public int getFoodRate() { return foodRate; }

    @JsonSetter("serviceRate")
    public void setServiceRate(int serviceRate) { this.serviceRate = serviceRate; }

    public int getServiceRate() { return this.serviceRate; }

    @JsonSetter("ambianceRate")
    public void setAmbianceRate(int ambianceRate) { this.ambianceRate = ambianceRate; }

    public int getAmbianceRate() { return this.ambianceRate; }

    @JsonSetter("overallRate")
    public void setOverallRate(int overallRate) { this.overallRate = overallRate; }

    public int getOverallRate() { return overallRate; }

    @JsonSetter("comment")
    public void setComment(String comment) { this.comment = comment; }

    public String getComment() { return this.comment; }

}
