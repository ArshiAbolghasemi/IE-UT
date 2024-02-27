package com.mizdooni.form;

import com.mizdooni.entity.*;
import com.mizdooni.form.restaurant.table.ReserveTableForm;
import com.mizdooni.repository.*;
import com.mizdooni.service.PasswordService;
import org.junit.After;
import org.junit.Before;

import java.time.LocalTime;

public class AddReviewFormTest {

    private AddressRepository addressRepository;

    private RestaurantAddressRepository restaurantAddressRepository;

    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;

    @Before
    public void setup() {
        this.userRepository = UserRepository.getInstance();
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.addressRepository = AddressRepository.getInstance();
        this.restaurantAddressRepository = RestaurantAddressRepository.getInstance();
        this.createRequiredEntities();
    }

    private void createRequiredEntities() {
        AddressEntity addressEntity = new AddressEntity()
                .setCity("city")
                .setCountry("country");
        this.addressRepository.persist(addressEntity);

        UserEntity client = new UserEntity()
                .setUsername("client")
                .setRole(UserEntity.ROLE_CLIENT)
                .setPassword(PasswordService.getInstance().hash("1234"))
                .setMail("client@example.com")
                .setAddressId(addressEntity.getId());
        this.userRepository.persist(client);

        UserEntity manager = new UserEntity()
                .setUsername(UserEntity.ROLE_MANAGER)
                .setPassword(PasswordService.getInstance().hash("4567"))
                .setMail("manager@example.com")
                .setAddressId(addressEntity.getId());
        this.userRepository.persist(manager);

        RestaurantAddressEntity restaurantAddressEntity = new RestaurantAddressEntity()
                .setCity("test_city")
                .setCountry("test_country")
                .setStreet("test_street");
        this.restaurantAddressRepository.persist(restaurantAddressEntity);

        RestaurantEntity restaurantEntity = new RestaurantEntity()
                .setName("restaurant")
                .setType("iranian")
                .setDescription("description")
                .setRestaurantAddressId(restaurantAddressEntity.getId())
                .setStartTime(LocalTime.parse("09:00"))
                .setEndTime(LocalTime.parse("23:00"));
        this.restaurantRepository.persist(restaurantEntity);
    }

    @After
    public void tearDown() {
        this.userRepository = null;
        this.restaurantRepository = null;
        this.restaurantAddressRepository = null;
        this.addressRepository = null;
    }
}
