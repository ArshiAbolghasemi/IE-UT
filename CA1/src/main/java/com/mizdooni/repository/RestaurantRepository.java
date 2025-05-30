package com.mizdooni.repository;

import com.mizdooni.entity.RestaurantEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public RestaurantEntity getRestaurantByName(String name) {
        Optional<RestaurantEntity> restaurantEntity = restaurants.stream()
                .filter(restaurant -> restaurant.getName().equals(name))
                .findFirst();

        return restaurantEntity.orElse(null);
    }

    public RestaurantEntity getRestaurantById(int restaurantId) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getId() == restaurantId)
                .findFirst()
                .orElse(null);
    }

    public List<RestaurantEntity> searchByName(String name) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<RestaurantEntity> searchByType(String type) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getType().equals(type))
                .collect(Collectors.toList());
    }

    public void persist(RestaurantEntity restaurantEntity) {
        restaurantEntity.setId(this.generateId());
        restaurants.add(restaurantEntity);
    }

    private int generateId() { return restaurants.size() + 1;}

}
