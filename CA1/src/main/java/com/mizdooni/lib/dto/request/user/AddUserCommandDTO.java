package com.mizdooni.lib.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.mizdooni.lib.dto.request.AddressDTO;

import java.util.Map;

public class AddUserCommandDTO {
    private String username;

    private String password;

    private String email;

    private String role;

    private AddressDTO address;

    @JsonSetter("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() { return this.username; }

    @JsonSetter("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() { return this.password; }

    @JsonSetter("role")
    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() { return this.role; }

    @JsonSetter("email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() { return this.email; }

    @JsonProperty("address")
    public void setAddress(Map<String,Object> address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCountry((String) address.get("country"));
        addressDTO.setCity((String) address.get("city"));
        this.address = addressDTO;
    }

    public AddressDTO getAddress() { return this.address; }

}
