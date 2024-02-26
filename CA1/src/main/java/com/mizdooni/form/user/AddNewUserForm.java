package com.mizdooni.form.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.lib.command.Form;
import com.mizdooni.lib.dto.request.user.AddUserCommandDTO;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.model.user.AddNewUserModel;
import com.mizdooni.repository.AddressRepository;
import com.mizdooni.repository.UserRepository;
import com.mizdooni.service.PasswordService;
import com.mizdooni.validator.user.AddressValidator;
import com.mizdooni.validator.user.EmailValidator;
import com.mizdooni.validator.user.PasswordValidator;
import com.mizdooni.validator.user.UsernameValidator;

public class AddNewUserForm implements Form {
    @Override
    public CommandResponseDTO getData(String[] args) {
        return new CommandResponseDTO().put("data", "User added successfully");
    }

    @Override
    public void execute(String[] args) throws RuntimeException {
        try {
            AddUserCommandDTO addUserCommandDTO = new ObjectMapper()
                    .readerFor(AddUserCommandDTO.class)
                    .readValue(args[0]);

            this.validate(addUserCommandDTO);
            (new AddNewUserModel(addUserCommandDTO,
                    UserRepository.getInstance(), AddressRepository.getInstance(),
                    PasswordService.getInstance())
            ).execute();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private void validate(AddUserCommandDTO addUserCommandDTO) throws IllegalArgumentException {
        (new UsernameValidator(addUserCommandDTO.getUsername())).validate();
        (new EmailValidator(addUserCommandDTO.getEmail())).validate();
        (new PasswordValidator(addUserCommandDTO.getPassword())).validate();
        (new AddressValidator(addUserCommandDTO.getAddress().getCountry(),
                addUserCommandDTO.getAddress().getCity())).validate();
    }
}
