package com.mizdooni.form.restaurant.table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.entity.TableEntity;
import com.mizdooni.entity.TableReservationEntity;
import com.mizdooni.entity.UserEntity;
import com.mizdooni.lib.command.Form;
import com.mizdooni.lib.dto.request.restaurant.table.ReserveTableCommandDTO;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.model.restaurant.table.ReserveTableModel;
import com.mizdooni.repository.RestaurantRepository;
import com.mizdooni.repository.TableRepository;
import com.mizdooni.repository.TableReservationRepository;
import com.mizdooni.repository.UserRepository;
import java.time.LocalDateTime;

public class ReserveTableForm implements Form {

    private int reservationId;

    @Override
    public CommandResponseDTO getData(String[] args) {
        return new CommandResponseDTO()
                .put("data", new CommandResponseDTO().put("reservationId", this.reservationId));
    }

    @Override
    public void execute(String[] args) throws RuntimeException {
        try {
            ReserveTableCommandDTO reserveTableCommandDTO = new ObjectMapper()
                    .readerFor(ReserveTableCommandDTO.class)
                    .readValue(args[0]);
            this.validate(reserveTableCommandDTO);
            ReserveTableModel reserveTableModel = new ReserveTableModel(reserveTableCommandDTO,
                    UserRepository.getInstance(), TableRepository.getInstance(),
                    RestaurantRepository.getInstance(), TableReservationRepository.getInstance());
            reserveTableModel.execute();
            this.reservationId = reserveTableModel.getTableReservation().getId();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(ReserveTableCommandDTO reserveTableCommandDTO) throws IllegalArgumentException {
        this.validateUsername(reserveTableCommandDTO.getUsername());
        this.validateDatetime(reserveTableCommandDTO.getDatetime());
        this.validateRestaurant(reserveTableCommandDTO.getRestaurantName());
        RestaurantEntity restaurant = RestaurantRepository.getInstance()
                .getRestaurantByName(reserveTableCommandDTO.getRestaurantName());
        TableEntity table = this.validateTable(reserveTableCommandDTO.getTableNumber(), restaurant);
        this.checkDatetimeIsInRestaurantWorkTime(reserveTableCommandDTO.getDatetime(), restaurant);
        this.checkTableHasNotAlreadyBeenReserved(table, reserveTableCommandDTO.getDatetime());
    }

    private void validateUsername(String username) throws IllegalArgumentException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("username should not be empty!");
        }

        UserEntity user = UserRepository.getInstance().getUserByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException(String.format("There is no %s user", username));
        }

        if (user.getRole().equals(UserEntity.ROLE_MANAGER)) {
            throw new IllegalArgumentException(String.format("user %s is manager!", username));
        }
    }

    private void validateDatetime(LocalDateTime datetime) throws IllegalArgumentException {
        if (datetime == null) {
            throw new IllegalArgumentException("datetime should not be empty!");
        }

        if (datetime.getMinute() != 0) {
            throw new IllegalArgumentException("invalid datetime format, minute should be equal to zero!");
        }

        if (!datetime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("datetime should be in future");
        }
    }

    private void validateRestaurant(String restaurantName) throws IllegalArgumentException {
        if (restaurantName == null || restaurantName.isEmpty()) {
            throw new IllegalArgumentException("restaurant name should not be empty");
        }

        RestaurantEntity restaurant = RestaurantRepository.getInstance().getRestaurantByName(restaurantName);

        if (restaurant == null) {
            throw new IllegalArgumentException(String.format("there is no restaurant with name %s", restaurantName));
        }
    }

    private TableEntity validateTable(int tableNumber, RestaurantEntity restaurant) throws IllegalArgumentException {
        if (tableNumber <= 0) {
            throw new IllegalArgumentException("invalid table argument!");
        }

        TableEntity table = TableRepository.getInstance().getTable(tableNumber, restaurant.getId());

        if (table == null) {
            throw new IllegalArgumentException(String.format("There is no table with number %d in restaurant %s",
                    tableNumber, restaurant.getName()));
        }

        return table;
    }

    private void checkDatetimeIsInRestaurantWorkTime(LocalDateTime dateTime, RestaurantEntity restaurant)
            throws IllegalArgumentException {
        if (dateTime.getHour() >= restaurant.getEndTime().getHour() ||
                dateTime.getHour() <= restaurant.getStartTime().getHour()) {
            throw new IllegalArgumentException("datetime is not in restaurant work time");
        }
    }

    private void checkTableHasNotAlreadyBeenReserved(TableEntity table, LocalDateTime dateTime) {
        TableReservationEntity tableReservation = TableReservationRepository.getInstance()
                .getTableReservation(table.getId(), dateTime, TableReservationEntity.STATUS_RESERVED);

        if (tableReservation != null) {
            throw new IllegalArgumentException(String.format("table %s is already reserved for %s", table,
                    dateTime.toString()));
        }
    }

}
