package com.mizdooni.model.user;

import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.entity.ReviewEntity;
import com.mizdooni.entity.UserEntity;
import com.mizdooni.lib.dto.request.user.AddReviewDTO;
import com.mizdooni.repository.RestaurantRepository;
import com.mizdooni.repository.ReviewRepository;
import com.mizdooni.repository.UserRepository;

public class AddReviewModel {

    private final AddReviewDTO addReviewDTO;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    public AddReviewModel(AddReviewDTO addReviewDTO,
                          ReviewRepository reviewRepository, UserRepository userRepository,
                          RestaurantRepository restaurantRepository) {
        this.addReviewDTO = addReviewDTO;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void execute() {
        this.createReviewEntity();
    }

    private void createReviewEntity() {
        UserEntity user = this.userRepository.getUserByUsername(addReviewDTO.getUsername());
        RestaurantEntity restaurant = this.restaurantRepository.getRestaurantByName(addReviewDTO.getRestaurantName());

        ReviewEntity reviewEntity = new ReviewEntity()
                .setUserId(user.getId())
                .setRestaurantId(restaurant.getId())
                .setFoodRate(addReviewDTO.getFoodRate())
                .setAmbianceRate(addReviewDTO.getAmbianceRate())
                .setServiceRate(addReviewDTO.getServiceRate())
                .setOverallRate(addReviewDTO.getOverallRate())
                .setComment(addReviewDTO.getComment());

        this.reviewRepository.persist(reviewEntity);
    }

}
