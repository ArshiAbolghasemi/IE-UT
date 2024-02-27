package com.mizdooni.form;

import com.mizdooni.entity.*;
import com.mizdooni.form.user.AddReviewForm;
import com.mizdooni.repository.*;
import com.mizdooni.service.PasswordService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AddReviewFormTest {

    private AddReviewForm addReviewForm;

    private AddressRepository addressRepository;

    private RestaurantAddressRepository restaurantAddressRepository;

    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;

    private ReviewRepository reviewRepository;

    @Before
    public void setup() {
        this.addReviewForm = new AddReviewForm();
        this.userRepository = UserRepository.getInstance();
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.addressRepository = AddressRepository.getInstance();
        this.restaurantAddressRepository = RestaurantAddressRepository.getInstance();
        this.reviewRepository = ReviewRepository.getInstance();
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
        this.addReviewForm = null;
        this.userRepository = null;
        this.restaurantRepository = null;
        this.restaurantAddressRepository = null;
        this.addressRepository = null;
        this.reviewRepository = null;
    }

    @Test
    public void testSuccessStory() {
        String[] args = {"{\"username\": \"client\", \"restaurantName\": \"restaurant\", \"foodRate\": 4.5, " +
                "\"serviceRate\": 3, \"ambianceRate\": 4.5, \"overallRate\": 4, \"comment\": \"not bad!\"}"};

        this.addReviewForm.execute(args);

        UserEntity user = this.userRepository.getUserByUsername("client");
        RestaurantEntity restaurant = this.restaurantRepository.getRestaurantByName("restaurant");
        ReviewEntity reviewEntity = this.reviewRepository.getReviewByUserIdAndRestaurantId(user.getId(), restaurant.getId());
        assertNotNull(reviewEntity);
        assertEquals(reviewEntity.getFoodRate(), 4.5, 0.0);
        assertEquals(reviewEntity.getServiceRate(), 3.0, 0.0);
        assertEquals(reviewEntity.getAmbianceRate(), 4.5, 0.0);
        assertEquals(reviewEntity.getOverallRate(), 4, 0.0);
        assertEquals(reviewEntity.getComment(), "not bad!");
    }

    @Test(expected = RuntimeException.class)
    public void testUndefinedUser() {
        String[] args = {"{\"username\": \"client1\", \"restaurantName\": \"restaurant\", \"foodRate\": 4.5, " +
                "\"serviceRate\": 3, \"ambianceRate\": 4.5, \"overallRate\": 4, \"comment\": \"not bad!\"}"};

        this.addReviewForm.execute(args);
    }

    @Test(expected = RuntimeException.class)
    public void testUndefinedRestaurant() {
        String[] args = {"{\"username\": \"client\", \"restaurantName\": \"restaurant1\", \"foodRate\": 4.5, " +
                "\"serviceRate\": 3, \"ambianceRate\": 4.5, \"overallRate\": 4, \"comment\": \"not bad!\"}"};

        this.addReviewForm.execute(args);
    }

    @Test(expected = RuntimeException.class)
    public void testManagerAddReview() {
        String[] args = {"{\"username\": \"manager\", \"restaurantName\": \"restaurant1\", \"foodRate\": 4.5, " +
                "\"serviceRate\": 3, \"ambianceRate\": 4.5, \"overallRate\": 4, \"comment\": \"not bad!\"}"};

        this.addReviewForm.execute(args);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidFoodRate() {
        String[] args = {"{\"username\": \"manager\", \"restaurantName\": \"restaurant1\", \"foodRate\": 6, " +
                "\"serviceRate\": 3, \"ambianceRate\": 4.5, \"overallRate\": 4, \"comment\": \"not bad!\"}"};

        this.addReviewForm.execute(args);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidServiceRate() {
        String[] args = {"{\"username\": \"manager\", \"restaurantName\": \"restaurant1\", \"foodRate\": 4.5, " +
                "\"serviceRate\": -1, \"ambianceRate\": 4.5, \"overallRate\": 4, \"comment\": \"not bad!\"}"};

        this.addReviewForm.execute(args);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidAmbianceRate() {
        String[] args = {"{\"username\": \"manager\", \"restaurantName\": \"restaurant1\", \"foodRate\": 4.5, " +
                "\"serviceRate\": 3, \"ambianceRate\": 7.4, \"overallRate\": 4, \"comment\": \"not bad!\"}"};

        this.addReviewForm.execute(args);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidOverallRate() {
        String[] args = {"{\"username\": \"manager\", \"restaurantName\": \"restaurant1\", \"foodRate\": 4.5, " +
                "\"serviceRate\": 3, \"ambianceRate\": 4.5, \"overallRate\": 5.1, \"comment\": \"not bad!\"}"};

        this.addReviewForm.execute(args);
    }

}
