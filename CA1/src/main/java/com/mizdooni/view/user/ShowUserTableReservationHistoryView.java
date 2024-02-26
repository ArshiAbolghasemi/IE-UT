package com.mizdooni.view.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.entity.TableReservationEntity;
import com.mizdooni.entity.UserEntity;
import com.mizdooni.lib.command.View;
import com.mizdooni.lib.dto.request.user.ShowTableReservationHistoryDTO;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.repository.TableReservationRepository;
import com.mizdooni.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ShowUserTableReservationHistoryView implements View {
    @Override
    public CommandResponseDTO getData(String[] args) {
        try {
            ShowTableReservationHistoryDTO showTableReservationHistoryDTO = new ObjectMapper()
                    .readerFor(ShowTableReservationHistoryDTO.class)
                    .readValue(args[0]);
            this.validate(showTableReservationHistoryDTO);
            UserEntity user = UserRepository.getInstance()
                    .getUserByUsername(showTableReservationHistoryDTO.getUsername());
            List<TableReservationEntity> tableReservations = TableReservationRepository.getInstance()
                    .getTableReservationsOfUser(user);

            return new CommandResponseDTO()
                    .put("data", new CommandResponseDTO()
                            .put("reservationHistory", tableReservations.stream()
                                    .map(tableReservation -> new CommandResponseDTO()
                                            .put("reservationNumber", tableReservation.getId())
                                            .put("restaurantName", tableReservation.getTable().getRestaurant()
                                                    .getName())
                                            .put("tableNumber", tableReservation.getTable().getTableNumber())
                                            .put("datetime", tableReservation.getReservationDate().toString())
                                    )
                                    .collect(Collectors.toList())
                            )
                    );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(ShowTableReservationHistoryDTO showTableReservationHistoryDTO)
            throws IllegalArgumentException {
        this.validateUsername(showTableReservationHistoryDTO.getUsername());
    }

    private void validateUsername(String username) throws IllegalArgumentException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("username should not be empty!");
        }

        UserEntity userEntity = UserRepository.getInstance().getUserByUsername(username);

        if (userEntity == null) {
            throw new IllegalArgumentException(String.format("user with username %s does not exist!", username));
        }

    }

}
