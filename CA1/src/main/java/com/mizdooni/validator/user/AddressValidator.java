package com.mizdooni.validator.user;

public class AddressValidator {

    private final String country;

    private final String city;

    public AddressValidator(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public void validate() throws AssertionError {
        this.validateCountry();
        this.validateCity();
    }

    private void validateCountry() throws AssertionError {
        assert this.country != null && this.country.isEmpty() : "address country should not be null";
    }

    private void validateCity() throws AssertionError {
        assert this.city != null && this.city.isEmpty() : "address city should not be null";
    }
}
