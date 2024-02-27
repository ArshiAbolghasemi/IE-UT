package com.mizdooni.lib.dto.response;

import com.mizdooni.entity.RestaurantEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SearchRestaurantResponseDTO extends CommandResponseDTO {

    public SearchRestaurantResponseDTO(List<RestaurantEntity> restaurantEntities) {
        this.put("data", new CommandResponseDTO()
                .put("restaurants", restaurantEntities.stream()
                        .map(restaurant -> new CommandResponseDTO()
                                .put("name", restaurant.getName())
                                .put("type", restaurant.getType())
                                .put("startTime", restaurant.getStartTime().toString())
                                .put("endTime", restaurant.getStartTime().toString())
                                .put("description", restaurant.getDescription())
                                .put("address", new CommandResponseDTO()
                                        .put("country", restaurant.getRestaurantAddress().getCountry())
                                        .put("city", restaurant.getRestaurantAddress().getCity())
                                        .put("street", restaurant.getRestaurantAddress().getStreet())
                                )
                        )
                        .collect(Collectors.toList())
                )
        );
    }
}
