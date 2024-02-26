package com.mizdooni.model.restaurant.table;

import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.entity.TableReservationEntity;
import com.mizdooni.lib.dto.request.restaurant.table.ReserveTableCommandDTO;
import com.mizdooni.repository.RestaurantRepository;
import com.mizdooni.repository.TableRepository;
import com.mizdooni.repository.TableReservationRepository;
import com.mizdooni.repository.UserRepository;

public class ReserveTableModel {

    private final ReserveTableCommandDTO reserveTableCommandDTO;

    private final UserRepository userRepository;

    private final TableRepository tableRepository;

    private final RestaurantRepository restaurantRepository;

    private final TableReservationRepository tableReservationRepository;

    private TableReservationEntity tableReservationEntity;

    public ReserveTableModel(ReserveTableCommandDTO reserveTableCommandDTO,
                             UserRepository userRepository, TableRepository tableRepository,
                             RestaurantRepository restaurantRepository,
                             TableReservationRepository tableReservationRepository) {
        this.reserveTableCommandDTO = reserveTableCommandDTO;
        this.userRepository = userRepository;
        this.tableRepository = tableRepository;
        this.restaurantRepository = restaurantRepository;
        this.tableReservationRepository = tableReservationRepository;
    }

    public void execute() {
        this.tableReservationEntity = this.createTableReservationEntity();
    }

    public TableReservationEntity getTableReservation() { return this.tableReservationEntity; }

    private TableReservationEntity createTableReservationEntity() {
        RestaurantEntity restaurantEntity = this.restaurantRepository
                .getRestaurantByName(this.reserveTableCommandDTO.getRestaurantName());

        TableReservationEntity tableReservation = new TableReservationEntity()
                .setUserId(this.userRepository.getUserByUsername(this.reserveTableCommandDTO.getUsername()).getId())
                .setTableId(this.tableRepository
                        .getTable(this.reserveTableCommandDTO.getTableNumber(), restaurantEntity.getId()).getId())
                .setReservationDate(this.reserveTableCommandDTO.getDatetime())
                .setStatus(TableReservationEntity.STATUS_RESERVED);
        this.tableReservationRepository.persist(tableReservation);

        return tableReservation;
    }
}
