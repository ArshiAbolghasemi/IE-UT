package com.mizdooni.form.restaurant.table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.entity.TableEntity;
import com.mizdooni.entity.UserEntity;
import com.mizdooni.lib.command.Form;
import com.mizdooni.lib.dto.request.restaurant.table.AddNewTableCommandDTO;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.model.restaurant.table.AddNewTableModel;
import com.mizdooni.repository.RestaurantRepository;
import com.mizdooni.repository.TableRepository;
import com.mizdooni.repository.UserRepository;

public class AddNewTableForm implements Form {
    @Override
    public CommandResponseDTO getData(String[] args) {
        return new CommandResponseDTO()
                .put("data", "Table added successfully");
    }

    @Override
    public void execute(String[] args) throws RuntimeException {
        try {
            AddNewTableCommandDTO addNewTableCommandDTO = new ObjectMapper()
                    .readerFor(AddNewTableCommandDTO.class)
                    .readValue(args[0]);
            this.validate(addNewTableCommandDTO);
            (new AddNewTableModel(addNewTableCommandDTO,
                    TableRepository.getInstance(), RestaurantRepository.getInstance())
            ).execute();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(AddNewTableCommandDTO addNewTableCommandDTO) throws IllegalArgumentException {
        this.validateManager(addNewTableCommandDTO.getManagerUsername());
        this.validateRestaurant(addNewTableCommandDTO.getRestaurantName());
        this.validateTable(addNewTableCommandDTO.getTableNumber(), addNewTableCommandDTO.getRestaurantName());
        this.validateSeatNumbers(addNewTableCommandDTO.getSeatsNumber());
        this.authenticateRestaurant(addNewTableCommandDTO.getManagerUsername(),
                addNewTableCommandDTO.getRestaurantName());
    }

    private void validateManager(String managerUsername) throws IllegalArgumentException {
        if (managerUsername == null || managerUsername.isEmpty()) {
            throw new IllegalArgumentException("manager username should not be empty");
        }

        UserEntity user = UserRepository.getInstance().getUserByUsername(managerUsername);

        if (user == null) {
            throw new IllegalArgumentException(String.format("There is not user with username %s", managerUsername));
        }

        if (!user.getRole().equals(UserEntity.ROLE_MANAGER)) {
            throw new IllegalArgumentException("User should has role manager");
        }
    }

    private void validateRestaurant(String restaurantName) throws IllegalArgumentException {
        if (restaurantName == null || restaurantName.isEmpty()) {
            throw new IllegalArgumentException("restaurant name should not be empty");
        }

        RestaurantEntity restaurant = RestaurantRepository.getInstance().getRestaurantByName(restaurantName);

        if (restaurant == null) {
            throw new IllegalArgumentException(String.format("There is no restaurant with name %s", restaurantName));
        }
    }

    private void validateTable(int tableNumber, String restaurantName) throws IllegalArgumentException {
        if (tableNumber <= 0) {
            throw new IllegalArgumentException("table number should be a natural number!");
        }

        RestaurantEntity restaurant = RestaurantRepository.getInstance().getRestaurantByName(restaurantName);
        TableEntity tableEntity = TableRepository.getInstance().getTable(tableNumber, restaurant.getId());

        if (tableEntity != null) {
            throw new IllegalArgumentException(String.format("table wit number %d is already exists in restaurant %s",
                    tableNumber, restaurantName));
        }
    }

    private void validateSeatNumbers(int seatNumbers) throws IllegalArgumentException {
        if (seatNumbers <= 0) {
            throw new IllegalArgumentException("seat numbers should be a natural number!");
        }
    }

    private void authenticateRestaurant(String managerUsername, String restaurantName) throws IllegalArgumentException {
        RestaurantEntity restaurant = RestaurantRepository.getInstance().getRestaurantByName(restaurantName);
        UserEntity manager = UserRepository.getInstance().getUserByUsername(managerUsername);

        if (manager.getId() != restaurant.getManagerUserId()) {
            throw new IllegalArgumentException("restaurant does not belong to manager!");
        }
    }

}
