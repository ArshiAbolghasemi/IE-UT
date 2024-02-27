package com.mizdooni.entity;

public class ReviewEntity {

    private int id;

    private int userId;

    private int restaurantId;

    private double foodRate;

    private double serviceRate;

    private double ambianceRate;

    private double overallRate;

    private String comment;

    public ReviewEntity setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() { return this.id; }

    public ReviewEntity setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getUserId() { return this.userId; }

    public ReviewEntity setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }

    public int getRestaurantId() { return this.restaurantId; }

    public ReviewEntity setFoodRate(double foodRate) {
        this.foodRate = foodRate;
        return this;
    }

    public double getFoodRate() { return this.foodRate; }

    public ReviewEntity setServiceRate(double serviceRate) {
        this.serviceRate = serviceRate;
        return this;
    }

    public double getServiceRate() { return this.serviceRate; }

    public ReviewEntity setAmbianceRate(double ambianceRate) {
        this.ambianceRate = ambianceRate;
        return this;
    }

    public double getAmbianceRate() { return ambianceRate; }

    public ReviewEntity setOverallRate(double overallRate) {
        this.overallRate = overallRate;
        return this;
    }

    public double getOverallRate() { return overallRate; }

    public ReviewEntity setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getComment() { return this.comment; }

}
