package com.mizdooni.model.restaurant.table;

import com.mizdooni.entity.TableReservationEntity;
import com.mizdooni.repository.TableReservationRepository;

public class CancelTableReservationModel {

    private final TableReservationEntity tableReservationEntity;

    private final TableReservationRepository tableReservationRepository;

    public CancelTableReservationModel(TableReservationRepository tableReservationRepository,
                                       TableReservationEntity tableReservationEntity) {
        this.tableReservationRepository = tableReservationRepository;
        this.tableReservationEntity = tableReservationEntity;
    }

    public void execute() {
        this.cancelReservation();
    }

    private void cancelReservation() {
        this.tableReservationEntity.setStatus(TableReservationEntity.STATUS_CANCELED);
        this.tableReservationRepository.persist(tableReservationEntity);
    }

}
