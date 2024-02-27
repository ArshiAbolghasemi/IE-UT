package com.mizdooni.form;

import com.mizdooni.entity.*;
import com.mizdooni.form.restaurant.table.ReserveTableForm;
import com.mizdooni.repository.*;
import com.mizdooni.service.PasswordService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class ReserveTableFormTest {

    private ReserveTableForm reserveTableForm;

    private TableReservationRepository tableReservationRepository;

    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;

    private AddressRepository addressRepository;

    private RestaurantAddressRepository restaurantAddressRepository;

    private TableRepository tableRepository;

    @Before
    public void setup() {
        this.reserveTableForm = new ReserveTableForm();
        this.tableReservationRepository = TableReservationRepository.getInstance();
        this.userRepository = UserRepository.getInstance();
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.tableRepository = TableRepository.getInstance();
        this.addressRepository = AddressRepository.getInstance();
        this.restaurantAddressRepository = RestaurantAddressRepository.getInstance();
        this.tableRepository = TableRepository.getInstance();
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

        TableEntity tableEntity = new TableEntity()
                .setTableNumber(1)
                .setRestaurantId(restaurantEntity.getId())
                .setSeatsNumber(2);
        this.tableRepository.persist(tableEntity);
    }

    @After
    public void tearDown() {
        this.reserveTableForm = null;
        this.tableReservationRepository = null;
        this.userRepository = null;
        this.restaurantRepository = null;
        this.tableRepository = null;
        this.restaurantAddressRepository = null;
        this.addressRepository = null;
    }

    @Test
    public void testSuccessStory() {
        String[] args = {
                "{\"username\": \"client\", \"restaurantName\": \"restaurant\", \"tableNumber\": \"1\", " +
                        "\"datetime\": \"2024-03-27 21:00\"}",
        };

        reserveTableForm.execute(args);

        RestaurantEntity restaurant = RestaurantRepository.getInstance().getRestaurantByName("restaurant");
        TableEntity table = TableRepository.getInstance().getTable(1, restaurant.getId());
        TableReservationEntity tableReservation = TableReservationRepository.getInstance()
                .getTableReservation(table.getId(),
                        LocalDateTime.parse("2024-03-27 21:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        TableReservationEntity.STATUS_RESERVED);
        UserEntity client = UserRepository.getInstance().getUserByUsername("client");

        assertEquals(tableReservation.getTableId(), table.getId());
        assertEquals(tableReservation.getUserId(), client.getId());
        assertEquals(tableReservation.getStatus(), TableReservationEntity.STATUS_RESERVED);
        assertEquals(tableReservation.getReservationDate(),
                LocalDateTime.parse("2024-03-27 21:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test(expected =  RuntimeException.class)
    public void testManagerReservedTableStory() {
        String[] args = {
                "{\"username\": \"manager\", \"restaurantName\": \"restaurant\", \"tableNumber\": \"1\", " +
                        "\"datetime\": \"2024-03-27 21:00\"}",
        };

        this.reserveTableForm.execute(args);
    }

    @Test(expected = RuntimeException.class)
    public void testUndefinedUser() {
        String[] args = {
                "{\"username\": \"client2\", \"restaurantName\": \"restaurant\", \"tableNumber\": \"1\", " +
                        "\"datetime\": \"2024-03-27 21:00\"}",
        };


        this.reserveTableForm.execute(args);
    }

}
