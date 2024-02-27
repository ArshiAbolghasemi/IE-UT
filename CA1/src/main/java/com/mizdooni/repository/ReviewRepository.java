package com.mizdooni.repository;

import com.mizdooni.entity.ReviewEntity;

import java.util.ArrayList;

public class ReviewRepository {

    private static ReviewRepository INSTANCE;

    private static ArrayList<ReviewEntity> reviews;

    private ReviewRepository() {}

    public static ReviewRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReviewRepository();
            reviews = new ArrayList<>();
        }

        return INSTANCE;
    }

    public ReviewEntity getReviewByUserIdAndRestaurantId(int userId, int restaurantId) {
        return reviews.stream()
                .filter(reviewEntity ->
                        reviewEntity.getUserId() == userId &&
                        reviewEntity.getRestaurantId() == restaurantId
                )
                .findFirst()
                .orElse(null);
    }

    public void persist(ReviewEntity reviewEntity) {
        reviewEntity.setId(this.generateId());
        reviews.add(reviewEntity);
    }

    private int generateId() { return reviews.size() + 1; }

}
