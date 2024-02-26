package com.mizdooni.model.restaurant.table;

import com.mizdooni.entity.TableEntity;
import com.mizdooni.lib.dto.request.restaurant.table.AddNewTableCommandDTO;
import com.mizdooni.repository.RestaurantRepository;
import com.mizdooni.repository.TableRepository;

public class AddNewTableModel {

    private final AddNewTableCommandDTO addNewTableCommandDTO;

    private final TableRepository tableRepository;

    private final RestaurantRepository restaurantRepository;

    public AddNewTableModel(AddNewTableCommandDTO addNewTableCommandDTO,
                            TableRepository tableRepository, RestaurantRepository restaurantRepository) {
        this.addNewTableCommandDTO = addNewTableCommandDTO;
        this.tableRepository = tableRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void execute() { this.createTableEntity(); }

    private void createTableEntity() {
        TableEntity table = new TableEntity()
                .setTableNumber(addNewTableCommandDTO.getTableNumber())
                .setRestaurantId(this.restaurantRepository
                        .getRestaurantByName(addNewTableCommandDTO.getRestaurantName()).getId())
                .setSeatsNumber(addNewTableCommandDTO.getSeatsNumber());
        this.tableRepository.persist(table);
    }

}
