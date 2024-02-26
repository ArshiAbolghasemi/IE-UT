package com.mizdooni.repository;

import com.mizdooni.entity.TableReservationEntity;
import java.util.ArrayList;

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

}
