package com.utc.app.safedriver.trip;

import org.eclipse.angus.mail.imap.protocol.Status;

import com.utc.app.safedriver.trip.status.StatusE;
import com.utc.app.safedriver.user.User;
import com.utc.app.safedriver.user.UserDTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {
    private Long id;
    private Long date_of_hire;
    private Long time_start;
    private Long time_end;
    private String pick_up_location;
    private String drop_off_location;
    private Double fee;
    private String license_plate;
    private String car_name;
    private String vehicle_type;
    private String range_of_vehicle;
    private String note;
    private StatusE status;
    private UserDTO user;

    public static TripDTO fromTrip(Trip trip) {
        return TripDTO.builder()
                .id(trip.getId())
                .date_of_hire(trip.getDate_of_hire())
                .time_start(trip.getTime_start())
                .pick_up_location(trip.getPick_up_location())
                .drop_off_location(trip.getDrop_off_location())
                .fee(trip.getFee())
                .license_plate(trip.getLicense_plate())
                .car_name(trip.getCar_name())
                .vehicle_type(trip.getVehicle_type())
                .range_of_vehicle(trip.getRange_of_vehicle())
                .note(trip.getNote())
                .status(trip.getStatus())
                .user(UserDTO.fromUser(trip.getUser()))
                .build();
    }
}
