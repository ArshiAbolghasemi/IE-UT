package com.mizdooni.validator.user;

import com.mizdooni.entity.UserEntity;
import com.mizdooni.repository.UserRepository;

public class UsernameValidator {

    private final String value;

    public UsernameValidator(String value) {
        this.value = value;
    }

    public void validate() throws AssertionError {
        this.validateFormat();
        this.checkUniqueness();
    }

    private void validateFormat() throws AssertionError {
        assert this.value != null && !this.value.isEmpty() : "username is required";
        assert this.value.matches("[a-zA-Z]+") : "username should only contains characters";
    }

    private void checkUniqueness() throws AssertionError {
        UserEntity user = UserRepository.getInstance().getUserByUsername(this.value);
        assert user == null : "username is used, you should choose another username";
    }

}
