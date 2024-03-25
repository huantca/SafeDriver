package com.utc.app.safedriver.trip;

import org.eclipse.angus.mail.imap.protocol.Status;

import com.utc.app.safedriver.trip.status.StatusE;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripRequest {
    private Long date_of_hire;
    private Long time_start;
    private String pick_up_location;
    private String drop_off_location;
    private String license_plate;
    private Double fee;
    private String car_name;
    private String vehicle_type;
    private String range_of_vehicle;
    private String note;
    private StatusE status;
}
