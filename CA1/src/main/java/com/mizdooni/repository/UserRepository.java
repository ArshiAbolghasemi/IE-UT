package com.mizdooni.repository;

import com.mizdooni.entity.UserEntity;
import java.util.ArrayList;
import java.util.Optional;

public class UserRepository {

    private static UserRepository INSTANCE;

    private static ArrayList<UserEntity> users;

    private UserRepository() {}

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            users = new ArrayList<>();
            INSTANCE = new UserRepository();
        }

        return INSTANCE;
    }

    public UserEntity getUser(String username) {
        Optional<UserEntity> userEntity = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        return userEntity.orElse(null);
    }
}
