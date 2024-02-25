package com.mizdooni.validator.user;

import com.mizdooni.entity.UserEntity;
import com.mizdooni.repository.UserRepository;

public class PasswordValidator {
    private final String value;

    public PasswordValidator(String value) {
        this.value = value;
    }

    public void validate() throws AssertionError {
        this.checkFormat();
    }

    public void checkFormat() {
        assert (this.value != null && !this.value.isEmpty()) : "password should not be empty";
    }
}
