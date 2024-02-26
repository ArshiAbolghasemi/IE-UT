package com.mizdooni.entity;

public class TableEntity {

    private int tableNumber;

    private int restaurantId;

    private int seatsNumber;

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

    public TableEntity setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
        return this;
    }

    public int getSeatsNumber() { return seatsNumber; }

}
