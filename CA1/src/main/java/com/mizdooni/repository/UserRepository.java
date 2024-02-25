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

    public UserEntity getUserByUsername(String username) {
        Optional<UserEntity> userEntity = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        return userEntity.orElse(null);
    }

    public UserEntity getUserByEmail(String email) {
        Optional<UserEntity> userEntity = users.stream()
                .filter(users -> users.getMail().equals(email))
                .findFirst();

        return userEntity.orElse(null);
    }
}
