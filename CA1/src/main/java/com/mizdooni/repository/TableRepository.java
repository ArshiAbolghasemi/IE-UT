package com.mizdooni.repository;

import com.mizdooni.entity.TableEntity;

import java.util.ArrayList;

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

}
