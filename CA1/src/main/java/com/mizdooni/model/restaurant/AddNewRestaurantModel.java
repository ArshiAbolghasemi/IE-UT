package com.mizdooni.model.restaurant;

import com.mizdooni.entity.RestaurantAddressEntity;
import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.entity.UserEntity;
import com.mizdooni.lib.dto.request.restaurant.AddNewRestaurantCommandDTO;
import com.mizdooni.repository.RestaurantAddressRepository;
import com.mizdooni.repository.RestaurantRepository;
import com.mizdooni.repository.UserRepository;

public class AddNewRestaurantModel {

    private final AddNewRestaurantCommandDTO addNewRestaurantCommandDTO;

    private final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    private final RestaurantAddressRepository restaurantAddressRepository;

    public AddNewRestaurantModel(AddNewRestaurantCommandDTO addNewRestaurantCommandDTO,
                                 RestaurantRepository restaurantRepository, UserRepository userRepository,
                                 RestaurantAddressRepository restaurantAddressRepository
    ) {
        this.addNewRestaurantCommandDTO = addNewRestaurantCommandDTO;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.restaurantAddressRepository = restaurantAddressRepository;
    }

    public void execute() {
        RestaurantAddressEntity restaurantAddressEntity = this.createRestaurantAddressEntity();
        UserEntity manager = this.userRepository.getUserByUsername(this.addNewRestaurantCommandDTO.getManagerUsername());
        this.createRestaurantEntity(manager, restaurantAddressEntity);
    }

    private RestaurantAddressEntity createRestaurantAddressEntity() {
        RestaurantAddressEntity restaurantAddress = this.restaurantAddressRepository.getRestaurantAddress(
                this.addNewRestaurantCommandDTO.getAddress().getCountry(),
                this.addNewRestaurantCommandDTO.getAddress().getCity(),
                this.addNewRestaurantCommandDTO.getAddress().getStreet()
        );

        if (restaurantAddress != null) return restaurantAddress;

        restaurantAddress = new RestaurantAddressEntity()
                .setCountry(this.addNewRestaurantCommandDTO.getAddress().getCountry())
                .setCity(this.addNewRestaurantCommandDTO.getAddress().getCity())
                .setStreet(this.addNewRestaurantCommandDTO.getAddress().getCity());
        this.restaurantAddressRepository.persist(restaurantAddress);

        return restaurantAddress;
    }

    private void createRestaurantEntity(UserEntity manager, RestaurantAddressEntity restaurantAddress) {
        RestaurantEntity restaurant = new RestaurantEntity()
                .setManagerUserId(manager.getId())
                .setRestaurantAddressId(restaurantAddress.getId())
                .setName(this.addNewRestaurantCommandDTO.getName())
                .setType(this.addNewRestaurantCommandDTO.getType())
                .setStartTime(this.addNewRestaurantCommandDTO.getStartTime())
                .setEndTime(this.addNewRestaurantCommandDTO.getEndTime())
                .setDescription(this.addNewRestaurantCommandDTO.getDescription());
        this.restaurantRepository.persist(restaurant);
    }

}
