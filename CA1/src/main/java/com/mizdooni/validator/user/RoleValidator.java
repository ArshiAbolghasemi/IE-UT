package com.mizdooni.validator.user;

import com.mizdooni.entity.UserEntity;

import java.util.Arrays;

public class RoleValidator {

    private final String value;

    public RoleValidator(String value) {
        this.value = value;
    }

    public void validate() throws IllegalArgumentException {
        this.validateFormat();
        this.checkValidUserRole();
    }

    private void validateFormat() throws IllegalArgumentException {
        if (this.value == null || this.value.isEmpty()) {
            throw new IllegalArgumentException("role should not be empty!");
        }
    }

    private void checkValidUserRole() throws IllegalArgumentException {
        if (!Arrays.asList(UserEntity.ALL_ROLE).contains(this.value)) {
            throw new IllegalArgumentException(
                    String.format("role should be in %s", String.join(", ", UserEntity.ALL_ROLE)));
        }
    }
}
