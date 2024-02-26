package com.mizdooni.repository;

import com.mizdooni.entity.RestaurantAddressEntity;

import java.util.ArrayList;

public class RestaurantAddressRepository {

    private static RestaurantAddressRepository INSTANCE;

    private static ArrayList<RestaurantAddressEntity> restaurantAddresses;

    private RestaurantAddressRepository() {}

    public static RestaurantAddressRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RestaurantAddressRepository();
            restaurantAddresses = new ArrayList<>();
        }

        return INSTANCE;
    }

}
