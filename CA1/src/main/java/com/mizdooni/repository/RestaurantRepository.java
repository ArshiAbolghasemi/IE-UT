package com.mizdooni.repository;

import com.mizdooni.entity.RestaurantEntity;
import java.util.ArrayList;

public class RestaurantRepository {

    private static RestaurantRepository INSTANCE;

    private static ArrayList<RestaurantEntity> restaurants;

    private RestaurantRepository() {}

    public static RestaurantRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RestaurantRepository();
            restaurants = new ArrayList<>();
        }

        return INSTANCE;
    }


}
