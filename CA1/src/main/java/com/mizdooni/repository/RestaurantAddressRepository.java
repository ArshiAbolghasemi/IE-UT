package com.mizdooni.repository;

import com.mizdooni.entity.RestaurantAddressEntity;
import com.mizdooni.entity.RestaurantEntity;

import java.util.ArrayList;
import java.util.Optional;

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

    public RestaurantAddressEntity getRestaurant(String country, String city, String street) {
        Optional<RestaurantAddressEntity> restaurantAddressEntity = restaurantAddresses.stream()
                .filter(restaurantAddress -> (
                    restaurantAddress.getCountry().equals(country) &&
                    restaurantAddress.getCity().equals(city) &&
                    restaurantAddress.getStreet().equals(street)
                ))
                .findFirst();

        return restaurantAddressEntity.orElse(null);
    }

}
