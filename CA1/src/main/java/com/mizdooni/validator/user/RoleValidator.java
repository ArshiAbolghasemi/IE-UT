package com.mizdooni.validator.user;

import com.mizdooni.entity.UserEntity;

import java.util.Arrays;

public class RoleValidator {

    private final String value;

    public RoleValidator(String value) {
        this.value = value;
    }

    public void validate() throws AssertionError {
        this.validateFormat();
        this.checkValidUserRole();
    }

    private void validateFormat() throws AssertionError {
        assert this.value != null && !this.value.isEmpty() : "role should not be empty!";
    }

    private void checkValidUserRole() throws AssertionError {
        assert Arrays.asList(UserEntity.ALL_ROLE).contains(this.value) :
                String.format("role should be in %s", String.join(", ", UserEntity.ALL_ROLE));
    }
}
