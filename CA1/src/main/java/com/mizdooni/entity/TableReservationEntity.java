package com.mizdooni.entity;

import java.time.LocalDate;

public class TableReservationEntity {

    private int id;

    private int userId;

    private int tableId;

    private LocalDate reservationDate;

    private boolean active;

    public TableReservationEntity setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() { return this.id; }

    public TableReservationEntity setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getUserId() { return this.userId; }

    public TableReservationEntity setTableId(int tableId) {
        this.tableId = tableId;
        return this;
    }

    public int getTableId() { return tableId; }

    public TableReservationEntity setReservationEntity(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
        return this;
    }

    public LocalDate getReservationDate() { return reservationDate; }

    public TableReservationEntity setActive(boolean active) {
        this.active = active;
        return this;
    }

    public boolean isActive() { return active; }

}
