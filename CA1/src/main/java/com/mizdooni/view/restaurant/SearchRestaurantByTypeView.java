package com.mizdooni.view.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.lib.command.View;
import com.mizdooni.lib.dto.request.restaurant.SearchRestaurantByTypeDTO;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.lib.dto.response.SearchRestaurantResponseDTO;
import com.mizdooni.repository.RestaurantRepository;

import java.util.List;

public class SearchRestaurantByTypeView implements View {
    @Override
    public CommandResponseDTO getData(String[] args) throws RuntimeException {
        try {
            SearchRestaurantByTypeDTO searchRestaurantByTypeDTO = new ObjectMapper()
                    .readerFor(SearchRestaurantByTypeDTO.class)
                    .readValue(args[0]);
            this.validate(searchRestaurantByTypeDTO);
            List<RestaurantEntity> restaurantEntities = RestaurantRepository.getInstance()
                    .searchByType(searchRestaurantByTypeDTO.getType());
            return new SearchRestaurantResponseDTO(restaurantEntities);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(SearchRestaurantByTypeDTO searchRestaurantByTypeDTO) throws IllegalArgumentException {
        String type = searchRestaurantByTypeDTO.getType();
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty!");
        }
    }
}
