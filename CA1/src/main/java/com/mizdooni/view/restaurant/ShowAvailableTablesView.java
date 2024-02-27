package com.mizdooni.view.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizdooni.entity.RestaurantEntity;
import com.mizdooni.entity.TableEntity;
import com.mizdooni.entity.TableReservationEntity;
import com.mizdooni.lib.command.View;
import com.mizdooni.lib.dto.request.restaurant.ShowAvailableTablesDTO;
import com.mizdooni.lib.dto.response.CommandResponseDTO;
import com.mizdooni.repository.RestaurantRepository;
import com.mizdooni.repository.TableRepository;
import com.mizdooni.repository.TableReservationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowAvailableTablesView implements View {
    @Override
    public CommandResponseDTO getData(String[] args) throws RuntimeException {
        try {
            ShowAvailableTablesDTO showAvailableTablesDTO = new ObjectMapper()
                    .readerFor(ShowAvailableTablesDTO.class)
                    .readValue(args[0]);
            this.validate(showAvailableTablesDTO);
            RestaurantEntity restaurant = RestaurantRepository.getInstance()
                    .getRestaurantByName(showAvailableTablesDTO.getRestaurantName());
            List<TableEntity> restaurantTables = TableRepository.getInstance().getRestaurantTables(restaurant);

            Map<TableEntity, List<TableReservationEntity>> reservationsPerTable = TableReservationRepository
                    .getInstance().getTablesReservationsGroupByTables(restaurantTables, LocalDate.now());

            List<LocalDateTime> availableTimeScopes = this.getAvailableTimeScopes(restaurant);

            Map<TableEntity, List<LocalDateTime>> availableTables = this.getAvailableTables(reservationsPerTable,
                    availableTimeScopes);

            for (Map.Entry<TableEntity, List<LocalDateTime>> entry : availableTables.entrySet()) {
                System.out.println("Key: " + entry.getKey().getTableNumber());
                System.out.println("value: " + entry.getValue());
                System.out.println("size: " + entry.getValue().size());
            }

            return new CommandResponseDTO()
                    .put("data", new CommandResponseDTO()
                            .put("availableTables", availableTables.entrySet().stream()
                                    .map(availableTable -> new CommandResponseDTO()
                                            .put("tableNumber", availableTable.getKey().getTableNumber())
                                            .put("seatsNumber", availableTable.getKey().getSeatsNumber())
                                            .put("availableTimes", availableTable.getValue().stream()
                                                    .map(LocalDateTime::toString)
                                                    .collect(Collectors.toList())
                                            )
                                    )
                                    .collect(Collectors.toList())
                            )
                    );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<TableEntity, List<LocalDateTime>> getAvailableTables(
            Map<TableEntity, List<TableReservationEntity>> reservationPerTable,
            List<LocalDateTime> availableTimeScopes) {

        Map<TableEntity, List<LocalDateTime>> mapTableToReservationDatetime = reservationPerTable.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        tableReservations -> tableReservations.getValue().stream()
                                .map(TableReservationEntity::getReservationDate)
                                .collect(Collectors.toList())
                ));

        return mapTableToReservationDatetime.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> availableTimeScopes.stream()
                                .filter(dateTime -> !entry.getValue().contains(dateTime))
                                .collect(Collectors.toList()))
                );
    }

    private List<LocalDateTime> getAvailableTimeScopes(RestaurantEntity restaurantEntity) {
        LocalDate now = LocalDate.now();
        return Stream
                .iterate(LocalDateTime.of(now, restaurantEntity.getStartTime()), dateTime -> dateTime.plusHours(1))
                .limit(restaurantEntity.getEndTime().getHour() - restaurantEntity.getStartTime().getHour() + 1)
                .collect(Collectors.toList());
    }

    private void validate(ShowAvailableTablesDTO showAvailableTablesDTO) throws IllegalArgumentException {
        String restaurantName = showAvailableTablesDTO.getRestaurantName();

        if (restaurantName == null || restaurantName.isEmpty()) {
            throw new IllegalArgumentException("restaurant name should not be empty!");
        }

        RestaurantEntity restaurant = RestaurantRepository.getInstance().getRestaurantByName(restaurantName);

        if (restaurant == null) {
            throw new IllegalArgumentException("invalid restaurant name!");
        }
    }

}
