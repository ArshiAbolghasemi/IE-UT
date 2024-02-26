package com.mizdooni.lib.dto.request.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class AddNewRestaurantCommandDTO {

    private String name;

    private String managerUsername;

    private String type;

    private LocalTime startTime;

    private LocalTime endTime;

    private String description;

    private RestaurantAddressDTO address;

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    @JsonSetter("managerUsername")
    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getManagerUsername() { return this.managerUsername; }

    @JsonSetter("type")
    public void setType(String type) {
        this.type = type;
    }

    public String getType() { return this.type; }

    @JsonSetter("startTime")
    public void setStartTime(String startTime) {
        try {
            this.startTime = LocalTime.parse(startTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("startTime format is not correct", e);
        }
    }

    public LocalTime getStartTime() { return this.startTime; }

    @JsonSetter("endTime")
    public void setEndTime(String endTime) {
        try {
            this.endTime = LocalTime.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("endTime format is not correct", e);
        }
    }

    public LocalTime getEndTime() { return this.endTime; }

    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() { return this.description; }

    @JsonProperty("address")
    public void setAddress(Map<String, Object> address) {
        RestaurantAddressDTO restaurantAddressDTO = new RestaurantAddressDTO();
        restaurantAddressDTO.setCountry((String) address.get("country"));
        restaurantAddressDTO.setCity((String) address.get("city"));
        restaurantAddressDTO.setStreet((String) address.get("street"));
        this.address = restaurantAddressDTO;
    }

    public RestaurantAddressDTO getAddress() { return this.address; }

}
