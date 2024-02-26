package com.mizdooni.lib.command;

import com.mizdooni.lib.dto.response.CommandResponseDTO;

public interface Command {
    public CommandResponseDTO getData(String[] args);
}
