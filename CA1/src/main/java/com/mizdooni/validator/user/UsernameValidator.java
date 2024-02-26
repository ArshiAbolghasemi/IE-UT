package com.mizdooni.validator.user;

import com.mizdooni.entity.UserEntity;
import com.mizdooni.repository.UserRepository;

public class UsernameValidator {

    private final String value;

    public UsernameValidator(String value) {
        this.value = value;
    }

    public void validate() throws IllegalArgumentException {
        this.validateFormat();
        this.checkUniqueness();
    }

    private void validateFormat() throws IllegalArgumentException {
        if (this.value == null || this.value.isEmpty()) {
            throw new IllegalArgumentException("username is required");
        }

        if (!this.value.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("username should only contains characters and number");
        }
    }

    private void checkUniqueness() throws IllegalArgumentException {
        UserEntity user = UserRepository.getInstance().getUserByUsername(this.value);
        if (user != null) {
            throw new IllegalArgumentException("username is used, you should choose another username");
        }
    }

}
