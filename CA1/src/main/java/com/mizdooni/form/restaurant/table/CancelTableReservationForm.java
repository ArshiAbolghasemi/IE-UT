package com.mizdooni.form.restaurant.table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.entity.TableReservationEntity;
import com.mizdooni.entity.UserEntity;
import com.mizdooni.lib.command.Form;
import com.mizdooni.lib.dto.request.restaurant.table.CancelTableReservationDTO;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.model.restaurant.table.CancelTableReservationModel;
import com.mizdooni.repository.TableReservationRepository;
import com.mizdooni.repository.UserRepository;

import java.time.LocalDateTime;

public class CancelTableReservationForm implements Form {
    @Override
    public CommandResponseDTO getData(String[] args) {
        return new CommandResponseDTO()
                .put("data", "Reservation cancelled successfully");
    }

    @Override
    public void execute(String[] args) throws RuntimeException {
        try {
            CancelTableReservationDTO cancelTableReservationDTO = new ObjectMapper()
                    .readerFor(CancelTableReservationDTO.class)
                    .readValue(args[0]);
            this.validate(cancelTableReservationDTO);
            (new CancelTableReservationModel(TableReservationRepository.getInstance(),
                    TableReservationRepository.getInstance().getById(cancelTableReservationDTO.getReservationNumber()))
            ).execute();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(CancelTableReservationDTO cancelTableReservationDTO) throws IllegalArgumentException {
        UserEntity user = this.validateUsername(cancelTableReservationDTO.getUsername());
        TableReservationEntity tableReservation = this.validateReservationNumber(
                cancelTableReservationDTO.getReservationNumber());
        this.authenticateSellerReservation(user, tableReservation);
        this.checkReservationDateIsFuture(tableReservation);
    }

    private UserEntity validateUsername(String username) throws IllegalArgumentException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("username should not be empty!");
        }

        UserEntity user = UserRepository.getInstance().getUserByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("invalid username!");
        }

        return user;
    }

    private TableReservationEntity validateReservationNumber(int reservationNumber) throws IllegalArgumentException {
        if (reservationNumber <= 0) {
            throw new IllegalArgumentException("invalid reservation number!");
        }

        TableReservationEntity tableReservation = TableReservationRepository.getInstance().getById(reservationNumber);

        if (tableReservation == null) {
            throw new IllegalArgumentException("reservation does not exist!");
        }

        return tableReservation;
    }

    private void authenticateSellerReservation(UserEntity user, TableReservationEntity tableReservationEntity)
        throws IllegalArgumentException {
        if (user.getId() != tableReservationEntity.getUserId()) {
            throw new IllegalArgumentException(String.format("table reservation %d does not belong to seller %d",
                    user.getId(), tableReservationEntity.getId()));
        }
    }

    private void checkReservationDateIsFuture(TableReservationEntity tableReservationEntity)
            throws IllegalArgumentException {
        if (!tableReservationEntity.getReservationDate().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("reservation is passed!");
        }
    }

}
