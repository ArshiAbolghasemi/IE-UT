package com.mizdooni.validator.user;

public class AddressValidator {

    private final String country;

    private final String city;

    public AddressValidator(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public void validate() throws IllegalArgumentException {
        this.validateCountry();
        this.validateCity();
    }

    private void validateCountry() throws IllegalArgumentException {
        if (this.country == null || this.country.isEmpty()) {
            throw new IllegalArgumentException("address country should not be null");
        }
    }

    private void validateCity() throws IllegalArgumentException {
        if (this.city == null || this.city.isEmpty()) {
            throw new IllegalArgumentException("address city should not be null");
        }
    }
}
