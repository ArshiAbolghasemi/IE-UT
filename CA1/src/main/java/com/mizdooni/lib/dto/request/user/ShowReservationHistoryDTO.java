package com.mizdooni.lib.dto.request.user;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ShowReservationHistoryDTO {

    private String username;

    @JsonSetter("username")
    public void setUsername(String username) { this.username = username; }

    public String getUsername() { return this.username; }

}
