package com.mizdooni.form.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.entity.UserEntity;
import com.mizdooni.lib.command.Form;
import com.mizdooni.lib.dto.request.restaurant.AddNewRestaurantCommandDTO;
import com.mizdooni.lib.dto.request.restaurant.RestaurantAddressDTO;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.model.restaurant.AddNewRestaurantModel;
import com.mizdooni.repository.RestaurantAddressRepository;
import com.mizdooni.repository.RestaurantRepository;
import com.mizdooni.repository.UserRepository;
import com.mizdooni.validator.user.AddressValidator;
import java.time.LocalTime;

public class AddNewRestaurantForm implements Form {
    @Override
    public CommandResponseDTO getData(String[] args) {
        return new CommandResponseDTO()
                .put("data", "Restaurant added successfully");
    }

    @Override
    public void execute(String[] args) throws RuntimeException {
        try {
            AddNewRestaurantCommandDTO addNewRestaurantCommandDTO = new ObjectMapper()
                    .readerFor(AddNewRestaurantCommandDTO.class)
                    .readValue(args[0]);

            this.validate(addNewRestaurantCommandDTO);
            (new AddNewRestaurantModel(addNewRestaurantCommandDTO,
                    RestaurantRepository.getInstance(), UserRepository.getInstance(),
                    RestaurantAddressRepository.getInstance())
            ).execute();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(AddNewRestaurantCommandDTO addNewRestaurantCommandDTO) throws IllegalArgumentException {
        this.validateName(addNewRestaurantCommandDTO.getName());
        this.validateManager(addNewRestaurantCommandDTO.getManagerUsername());
        this.validateType(addNewRestaurantCommandDTO.getType());
        this.validateStartTime(addNewRestaurantCommandDTO.getStartTime());
        this.validateEndTime(addNewRestaurantCommandDTO.getEndTime());
        this.validateDescription(addNewRestaurantCommandDTO.getDescription());
        this.validateAddress(addNewRestaurantCommandDTO.getAddress());
    }

    private void validateName(String restaurantName) throws IllegalArgumentException {
        if (restaurantName == null || restaurantName.isEmpty()) {
            throw new IllegalArgumentException("restaurant name should not be empty");
        }

        RestaurantEntity restaurant = RestaurantRepository.getInstance().getRestaurantByName(restaurantName);

        if (restaurant != null) {
            throw new IllegalArgumentException(String.format("restaurant with name %s already exists", restaurantName));
        }
    }

    private void validateManager(String managerUsername) throws IllegalArgumentException {
        if (managerUsername == null || managerUsername.isEmpty()) {
            throw new IllegalArgumentException("manager username should not be empty");
        }

        UserEntity user = UserRepository.getInstance().getUserByUsername(managerUsername);
        if (user == null) {
            throw new IllegalArgumentException("manager does not exist");
        }

        if (!user.getRole().equals(UserEntity.ROLE_MANAGER)) {
            throw new IllegalArgumentException("manager should have role manager");
        }
    }

    private void validateType(String type) throws IllegalArgumentException {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty");
        }
    }

    private void validateStartTime(LocalTime startTime) throws IllegalArgumentException {
        if (startTime == null) {
            throw new IllegalArgumentException("start time should not be empty");
        }

        if (startTime.getMinute() != 0) {
            throw new IllegalArgumentException("start time should not have minute");
        }
    }

    private void validateEndTime(LocalTime endTime) throws IllegalArgumentException {
        if (endTime == null) {
            throw new IllegalArgumentException("start time should not be empty");
        }

        if (endTime.getMinute() != 0) {
            throw new IllegalArgumentException("start time should not have minute");
        }
    }

    private void validateDescription(String description) throws IllegalArgumentException {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description should not be empty");
        }
    }

    private void validateAddress(RestaurantAddressDTO restaurantAddressDTO) throws IllegalArgumentException {
        (new AddressValidator(restaurantAddressDTO.getCountry(), restaurantAddressDTO.getCity())).validate();
        if (restaurantAddressDTO.getStreet() == null || restaurantAddressDTO.getStreet().isEmpty()) {
            throw new IllegalArgumentException("street of address should not be empty");
        }
    }

}
