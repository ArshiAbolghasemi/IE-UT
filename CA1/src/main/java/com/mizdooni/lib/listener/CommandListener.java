package com.mizdooni.lib.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.form.restaurant.AddNewRestaurantForm;
import com.mizdooni.form.restaurant.table.AddNewTableForm;
import com.mizdooni.form.restaurant.table.CancelTableReservationForm;
import com.mizdooni.form.restaurant.table.ReserveTableForm;
import com.mizdooni.form.user.AddNewUserForm;
import com.mizdooni.lib.command.Command;
import com.mizdooni.lib.command.Form;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.view.restaurant.SearchRestaurantByNameView;
import com.mizdooni.view.restaurant.SearchRestaurantByTypeView;
import com.mizdooni.view.user.ShowUserTableReservationHistoryView;

import java.util.HashMap;
import java.util.Map;

public class CommandListener {

    private final Map<String, String> commands = new HashMap<>();

    public CommandListener() {
        this.init();
    }

    private void init() {
        this
                .register("addUser", AddNewUserForm.class.getName())
                .register("addRestaurant", AddNewRestaurantForm.class.getName())
                .register("addTable", AddNewTableForm.class.getName())
                .register("reserveTable", ReserveTableForm.class.getName())
                .register("cancelReservation", CancelTableReservationForm.class.getName())
                .register("showReservationHistory", ShowUserTableReservationHistoryView.class.getName())
                .register("searchRestaurantsByName", SearchRestaurantByNameView.class.getName())
                .register("searchRestaurantsByType", SearchRestaurantByTypeView.class.getName());
    }

    private CommandListener register(String commandName, String commandClass) throws RuntimeException {
        try {
            if (!Command.class.isAssignableFrom(Class.forName(commandClass))) {
                throw new IllegalArgumentException(
                        String.format("%s should implement %s", commandClass, Command.class.getName()));
            }

            this.commands.put(commandName, commandClass);

            return this;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Command find(String command) throws Exception {
        if (!this.commands.containsKey(command)) {
            throw new Exception("command doesn't exists!");
        }

        return (Command) Class.forName(this.commands.get(command)).getDeclaredConstructor().newInstance();
    }

    private void successResponse(CommandResponseDTO commandResponseDTO) {
        this.response(commandResponseDTO, true);
    }

    private void errorResponse(CommandResponseDTO commandResponseDTO) {
        this.response(commandResponseDTO, false);
    }

    private void response(CommandResponseDTO commandResponseDTO, boolean success) {
        commandResponseDTO.put("success", success);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(commandResponseDTO));
        } catch (JsonProcessingException e) {
            System.out.println("something goes wrong!");
        }
    }

    public void run(String cmd, String[] args) {
        try {
            Command command = this.find(cmd);

            if (command instanceof Form) {
                ((Form) command).execute(args);
            }

            CommandResponseDTO commandResponseDTO = command.getData(args);
            this.successResponse(commandResponseDTO);
        } catch (Throwable e) {
            CommandResponseDTO commandResponseDTO = new CommandResponseDTO();
            commandResponseDTO.put("data", e.getMessage());
            this.errorResponse(commandResponseDTO);
        }
    }
}
