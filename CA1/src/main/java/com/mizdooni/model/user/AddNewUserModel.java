package com.mizdooni.model.user;

import com.mizdooni.entity.AddressEntity;
import com.mizdooni.entity.UserEntity;
import com.mizdooni.lib.dto.request.user.AddUserCommandDTO;
import com.mizdooni.repository.AddressRepository;
import com.mizdooni.repository.UserRepository;
import com.mizdooni.service.PasswordService;

public class AddNewUserModel {

    private final AddUserCommandDTO addUserCommandDTO;

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private final PasswordService passwordService;

    public AddNewUserModel(AddUserCommandDTO addUserCommandDTO,
                           UserRepository userRepository, AddressRepository addressRepository,
                           PasswordService passwordService) {
        this.addUserCommandDTO = addUserCommandDTO;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordService = passwordService;
    }

    public void execute() throws RuntimeException {
        AddressEntity address = this.addressRepository.getAddress(this.addUserCommandDTO.getAddress().getCountry(),
                this.addUserCommandDTO.getAddress().getCity());
        if (address == null) {
            address = this.createAddressEntity();
            this.addressRepository.persist(address);
        }
        UserEntity user = this.createUserEntity(address);
        this.userRepository.persist(user);
    }

    private UserEntity createUserEntity(AddressEntity address) throws RuntimeException {
        return new UserEntity()
                .setUsername(this.addUserCommandDTO.getUsername())
                .setPassword(this.passwordService.hash(this.addUserCommandDTO.getPassword()))
                .setMail(addUserCommandDTO.getEmail())
                .setRole(addUserCommandDTO.getRole())
                .setAddressId(address.getId());
    }

    private AddressEntity createAddressEntity() {
        return new AddressEntity()
                .setCity(this.addUserCommandDTO.getAddress().getCity())
                .setCountry(this.addUserCommandDTO.getAddress().getCountry());
    }

}
