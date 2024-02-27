package com.mizdooni.form.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.entity.UserEntity;
import com.mizdooni.lib.command.Form;
import com.mizdooni.lib.dto.request.user.AddReviewDTO;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.model.user.AddReviewModel;
import com.mizdooni.repository.RestaurantRepository;
import com.mizdooni.repository.ReviewRepository;
import com.mizdooni.repository.UserRepository;

public class AddReviewForm implements Form  {
    @Override
    public CommandResponseDTO getData(String[] args) {
        return new CommandResponseDTO()
                .put("data", "Review added successfully");
    }

    @Override
    public void execute(String[] args) throws RuntimeException {
        try {
            AddReviewDTO addReviewDTO = (new ObjectMapper())
                    .readerFor(AddReviewDTO.class)
                    .readValue(args[0]);
            this.validate(addReviewDTO);
            (new AddReviewModel(addReviewDTO,
                    ReviewRepository.getInstance(), UserRepository.getInstance(), RestaurantRepository.getInstance()
            )).execute();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(AddReviewDTO addReviewDTO) throws IllegalArgumentException {
        this.validateUsername(addReviewDTO.getUsername());
        this.validateRestaurantName(addReviewDTO.getRestaurantName());
        this.validateFoodRate(addReviewDTO.getFoodRate());
        this.validateServiceRate(addReviewDTO.getServiceRate());
        this.validateAmbianceRate(addReviewDTO.getAmbianceRate());
        this.validateOverallRate(addReviewDTO.getOverallRate());
        this.validateComment(addReviewDTO.getComment());
    }

    private void validateUsername(String username) throws IllegalArgumentException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("username should not be empty!");
        }

        UserEntity user = UserRepository.getInstance().getUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException(String.format("There is no user %s", username));
        }

        if (user.getRole().equals(UserEntity.ROLE_MANAGER)) {
            throw new IllegalArgumentException("manager cannot add review!");
        }
    }

    private void validateRestaurantName(String restaurantName) throws IllegalArgumentException {
        if (restaurantName == null || restaurantName.isEmpty()) {
            throw new IllegalArgumentException("restaurant name should be empty!");
        }

        RestaurantEntity restaurant = RestaurantRepository.getInstance().getRestaurantByName(restaurantName);
        if (restaurant == null) {
            throw new IllegalArgumentException(String.format("restaurant %s is not exist", restaurantName));
        }
    }

    private void validateFoodRate(double foodRate) throws IllegalArgumentException {
        if (this.isInvalidRateRange(foodRate)) {
            throw new IllegalArgumentException("food rate should be in range 0 to 5!");
        }
    }

    private void validateServiceRate(double serviceRate) throws IllegalArgumentException {
        if (this.isInvalidRateRange(serviceRate)) {
            throw new IllegalArgumentException("service rate should be in range 0 to 5!");
        }
    }

    private void validateAmbianceRate(double ambianceRate) throws IllegalArgumentException {
        if (this.isInvalidRateRange(ambianceRate)) {
            throw new IllegalArgumentException("ambiance rate should be in range 0 to 5!");
        }
    }

    private void validateOverallRate(double overallRate) throws IllegalArgumentException {
        if (this.isInvalidRateRange(overallRate)) {
            throw new IllegalArgumentException("overall rate should be in range 0 to 5!");
        }
    }

    private void validateComment(String comment) throws IllegalArgumentException {
        if (comment == null || comment.isEmpty()) {
            throw new IllegalArgumentException("comment should not be empty!");
        }
    }

    private boolean isInvalidRateRange(double rate) {
        return rate <= 0 || rate >= 5;
    }

}
