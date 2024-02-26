package com.mizdooni.lib.dto.request.restaurant.table;

import com.fasterxml.jackson.annotation.JsonSetter;

public class CancelTableReservationDTO {

    private String username;

    private int reservationNumber;

    @JsonSetter("username")
    public void setUsername(String username) { this.username = username; }

    public String getUsername() { return this.username; }

    @JsonSetter("reservationNumber")
    public void setReservationNumber(int reservationNumber) { this.reservationNumber = reservationNumber; }

    public int getReservationNumber() { return this.reservationNumber; }

}
