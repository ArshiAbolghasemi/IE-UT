package com.mizdooni.entity;

import com.mizdooni.repository.RestaurantRepository;

public class TableEntity {

    private int id;

    private int tableNumber;

    private int restaurantId;

    private int seatsNumber;

    public TableEntity setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() { return this.id; }

    public TableEntity setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
        return this;
    }

    public int getTableNumber() { return tableNumber; }

    public TableEntity setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }

    public int getRestaurantId() { return restaurantId; }

    public RestaurantEntity getRestaurant() {
        return RestaurantRepository.getInstance().getRestaurantById(this.restaurantId);
    }

    public TableEntity setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
        return this;
    }

    public int getSeatsNumber() { return seatsNumber; }

}
