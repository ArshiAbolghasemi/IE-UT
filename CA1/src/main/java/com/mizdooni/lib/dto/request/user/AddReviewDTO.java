package com.mizdooni.lib.dto.request.user;

import com.fasterxml.jackson.annotation.JsonSetter;

public class AddReviewDTO {

    private String username;

    private String restaurantName;

    private double foodRate;

    private double serviceRate;

    private double ambianceRate;

    private double overallRate;

    private String comment;

    @JsonSetter("username")
    public void setUsername(String username) { this.username = username; }

    public String getUsername() { return username; }

    @JsonSetter("restaurantName")
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getRestaurantName() { return this.restaurantName; }

    @JsonSetter("foodRate")
    public void setFoodRate(double foodRate) { this.foodRate = foodRate; }

    public double getFoodRate() { return foodRate; }

    @JsonSetter("serviceRate")
    public void setServiceRate(double serviceRate) { this.serviceRate = serviceRate; }

    public double getServiceRate() { return this.serviceRate; }

    @JsonSetter("ambianceRate")
    public void setAmbianceRate(double ambianceRate) { this.ambianceRate = ambianceRate; }

    public double getAmbianceRate() { return this.ambianceRate; }

    @JsonSetter("overallRate")
    public void setOverallRate(double overallRate) { this.overallRate = overallRate; }

    public double getOverallRate() { return overallRate; }

    @JsonSetter("comment")
    public void setComment(String comment) { this.comment = comment; }

    public String getComment() { return this.comment; }

}
