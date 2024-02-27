package com.mizdooni.repository;

import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.entity.TableEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TableRepository {

    private static TableRepository INSTANCE;

    private static ArrayList<TableEntity> tables;

    private TableRepository() {}

    public static TableRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TableRepository();
            tables = new ArrayList<>();
        }

        return INSTANCE;
    }

    public TableEntity getTable(int tableNumber, int restaurantId) {
        Optional<TableEntity> tableEntity = tables.stream()
                .filter(table -> (
                        table.getTableNumber() == tableNumber &&
                        table.getRestaurantId() == restaurantId
                ))
                .findFirst();

        return tableEntity.orElse(null);
    }

    public TableEntity geTabletById(int tableId) {
        Optional<TableEntity> tableEntity = tables.stream()
                .filter(table -> table.getId() == tableId)
                .findFirst();

        return tableEntity.orElse(null);
    }

    public List<TableEntity> getRestaurantTables(RestaurantEntity restaurant) {
        return tables.stream()
                .filter(table -> table.getRestaurantId() == restaurant.getId())
                .collect(Collectors.toList());
    }

    public void persist(TableEntity table) {
        table.setId(this.generateId());
        tables.add(table);
    }

    private int generateId() { return tables.size() + 1; }

}
