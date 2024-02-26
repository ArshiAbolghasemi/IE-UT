package com.mizdooni.lib.dto.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;

public class CommandResponseDTO {
    private final Map<String, Object> properties = new HashMap<>();

    public CommandResponseDTO put(String key, Object object) {
        this.properties.put(key, object);
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return this.properties;
    }

}
