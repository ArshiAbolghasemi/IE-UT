package com.mizdooni.repository;

import com.mizdooni.entity.UserEntity;
import java.util.ArrayList;

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

}
