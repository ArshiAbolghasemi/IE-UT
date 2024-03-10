package com.ut.mizdooni.ca2.entity;

import com.ut.mizdooni.ca2.lib.exception.ValidateException;

import java.util.Arrays;

public class UserEntity {

    public static final String ROLE_CLIENT = "client";
    public static final String ROLE_MANAGER = "manager";
    public static final String[] ALL_ROLE = {
            UserEntity.ROLE_CLIENT,
            UserEntity.ROLE_MANAGER,
    };

    private int id;

    private String username;

    private String password;

    private String mail;

    private String role;

    private int addressId;

    public UserEntity() {}

    public int getId() { return this.id; }

    public UserEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() { return this.username; }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() { return this.password; }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMail() { return this.mail; }

    public UserEntity setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getRole() { return this.role; }

    public UserEntity setRole(String role) throws ValidateException {
        if (!Arrays.asList(UserEntity.ALL_ROLE).contains(role)) {
            throw new ValidateException("Invalid role!", "role");
        }

        this.role = role;
        return this;
    }

    public int getAddressId() { return this.addressId; }

    public UserEntity setAddressId(int addressId) {
        this.addressId = addressId;
        return this;
    }

}
