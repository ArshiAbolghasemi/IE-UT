package com.mizdooni.lib.dto.request.restaurant.table;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ReserveTableCommandDTO {

    private String username;

    private String restaurantName;

    private int tableNumber;

    private LocalDate datetime;

    @JsonSetter("username")
    public void setUsername(String username) { this.username = username; }

    public String getUsername() { return this.username; }

    @JsonSetter("restaurantName")
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getRestaurantName() { return this.restaurantName; }

    @JsonSetter("tableNumber")
    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }

    public int getTableNumber() { return this.tableNumber; }

    @JsonSetter("datetime")
    public void setDatetime(String datetime) {
        String format = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            this.datetime = LocalDate.parse(datetime, formatter);
        } catch (DateTimeParseException exception) {
            throw new RuntimeException(String.format("datetime should be format %s", format));
        }
    }

    public LocalDate getDatetime() { return datetime; }

}
