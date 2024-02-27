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

    public void persist(ReviewEntity reviewEntity) {
        reviewEntity.setId(this.generateId());
        reviews.add(reviewEntity);
    }

    private int generateId() { return reviews.size() + 1; }

}
