package com.mizdooni.repository;

import com.mizdooni.entity.TableReservationEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class TableReservationRepository {

    private static TableReservationRepository INSTANCE;

    private static ArrayList<TableReservationEntity> tableReservations;

    private TableReservationRepository() {}

    public static TableReservationRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TableReservationRepository();
            tableReservations = new ArrayList<>();
        }

        return INSTANCE;
    }

    public TableReservationEntity getTableReservation(int tableId, LocalDateTime reservationDate, String status) {
        Optional<TableReservationEntity> tableReservationEntity = tableReservations.stream()
                .filter(tableReservation -> tableReservation.getTableId() == tableId &&
                        tableReservation.getReservationDate().isEqual(reservationDate) &&
                        tableReservation.getStatus().equals(status))
                .findFirst();

        return tableReservationEntity.orElse(null);
    }

}
