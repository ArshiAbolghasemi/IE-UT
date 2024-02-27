package com.mizdooni.view.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.lib.command.View;
import com.mizdooni.lib.dto.request.restaurant.SearchRestaurantByNameDTO;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.lib.dto.response.SearchRestaurantResponseDTO;
import com.mizdooni.repository.RestaurantRepository;

import java.util.List;

public class SearchRestaurantByNameView implements View {

    @Override
    public CommandResponseDTO getData(String[] args) throws RuntimeException {
        try {
            SearchRestaurantByNameDTO searchRestaurantByNameDTO = new ObjectMapper()
                    .readerFor(SearchRestaurantByNameDTO.class)
                    .readValue(args[0]);
            this.validate(searchRestaurantByNameDTO);
            List<RestaurantEntity> restaurantEntities = RestaurantRepository.getInstance()
                    .searchByName(searchRestaurantByNameDTO.getName());
            return new SearchRestaurantResponseDTO(restaurantEntities);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(SearchRestaurantByNameDTO searchRestaurantByNameDTO) throws IllegalArgumentException {
        String name = searchRestaurantByNameDTO.getName();
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name should not be empty");
        }
    }
}
