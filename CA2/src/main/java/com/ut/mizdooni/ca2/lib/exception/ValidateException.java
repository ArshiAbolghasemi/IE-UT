package com.ut.mizdooni.ca2.lib.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidateException extends Exception {

    private final Map<String, String> errors;

    public ValidateException(String message, String code) {
        super(message);
        this.errors = new HashMap<>();
        this.errors.put(code, message);
    }

    public Map<String, String> getErrors() { return this.errors; }
}
