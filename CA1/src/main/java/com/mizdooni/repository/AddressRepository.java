package com.mizdooni.repository;

import com.mizdooni.entity.AddressEntity;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class AddressRepository {

    private static AddressRepository INSTANCE;

    private static ArrayList<AddressEntity> addresses;

    private AddressRepository() {}

    public static AddressRepository getInstance() {
        if (INSTANCE == null) {
            addresses = new ArrayList<>();
            INSTANCE = new AddressRepository();
        }

        return INSTANCE;
    }

    public AddressEntity getAddress(String country, String city) {
        Optional<AddressEntity> addressEntity = addresses.stream()
                .filter(address -> (
                        Objects.equals(address.getCountry(), country) &&
                        Objects.equals(address.getCity(), city)
                ))
                .findFirst();

        return addressEntity.orElse(null);
    }

    public void persist(AddressEntity address) {
        address.setId(this.generateId());
        addresses.add(address);
    }

    private int generateId() { return addresses.size() + 1; }
}
