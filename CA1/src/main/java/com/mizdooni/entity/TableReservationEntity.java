package com.mizdooni.entity;

import com.mizdooni.repository.TableRepository;

import java.time.LocalDateTime;
import java.util.Arrays;

public class TableReservationEntity {

    public static final String STATUS_RESERVED = "reserved";

    public static final String STATUS_CANCELED = "canceled";

    public static final String[] ALL_STATUS = {
            TableReservationEntity.STATUS_RESERVED,
            TableReservationEntity.STATUS_CANCELED,
    };

    private int id;

    private int userId;

    private int tableId;

    private LocalDateTime reservationDate;

    private String status;

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

    public TableEntity getTable() { return TableRepository.getInstance().geTabletById(this.tableId); }

    public TableReservationEntity setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
        return this;
    }

    public LocalDateTime getReservationDate() { return reservationDate; }

    public TableReservationEntity setStatus(String status) throws IllegalArgumentException {
        if (!Arrays.asList(TableReservationEntity.ALL_STATUS).contains(status)) {
            throw new IllegalArgumentException(String.format("status should be in %s",
                    String.join(", ", TableReservationEntity.ALL_STATUS)));
        }

        this.status = status;
        return this;
    }

    public String getStatus() { return status; }

}
