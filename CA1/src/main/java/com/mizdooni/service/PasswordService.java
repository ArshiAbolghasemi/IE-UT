package com.mizdooni.service;

public class PasswordService {

    private static PasswordService INSTANCE;

    private PasswordService() {}

    public static PasswordService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PasswordService();
        }

        return INSTANCE;
    }
}
