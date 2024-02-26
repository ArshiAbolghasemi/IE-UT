package com.mizdooni.validator.user;

import com.mizdooni.entity.UserEntity;
import com.mizdooni.repository.UserRepository;

public class PasswordValidator {
    private final String value;

    public PasswordValidator(String value) {
        this.value = value;
    }

    public void validate() throws IllegalArgumentException {
        this.checkFormat();
    }

    public void checkFormat() throws IllegalArgumentException {
        if (this.value == null || this.value.isEmpty()) {
            throw new IllegalArgumentException("password should not be empty");
        }
    }
}
