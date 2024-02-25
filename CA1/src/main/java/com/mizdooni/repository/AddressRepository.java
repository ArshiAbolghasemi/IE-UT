package com.mizdooni.repository;

import com.mizdooni.entity.AddressEntity;
import java.util.ArrayList;

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

    public void persist(AddressEntity address) {
        address.setId(this.generateId());
        addresses.add(address);
    }

    private int generateId() { return addresses.size(); }
}
