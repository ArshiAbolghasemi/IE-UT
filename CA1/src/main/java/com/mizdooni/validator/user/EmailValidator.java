package com.mizdooni.validator.user;

import com.mizdooni.entity.UserEntity;
import com.mizdooni.repository.UserRepository;

public class EmailValidator {

    public static final String REGEX =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private final String value;

    public EmailValidator(String value) {
        this.value = value;
    }

    public void validate() throws AssertionError {
        this.checkFormat();
        this.checkUniqueness();
    }

    private void checkFormat() {
        assert this.value.matches(REGEX) : "invalid email address!";
    }

    private void checkUniqueness() {
        UserEntity user = UserRepository.getInstance().getUserByEmail(this.value);
        assert user == null : "user with this email is exists!";
    }
}
