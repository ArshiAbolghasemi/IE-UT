package com.mizdooni.entity;

public class TableEntity {

    private int tableNumber;

    private int restaurantId;

    private int seatNumbers;

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

    public TableEntity setSeatNumbers(int seatNumbers) {
        this.seatNumbers = seatNumbers;
        return this;
    }

    public int getSeatNumbers() { return seatNumbers; }

}
