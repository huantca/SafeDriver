package com.utc.app.safedriver.trip;

import java.util.List;

import org.eclipse.angus.mail.imap.protocol.Status;

import com.utc.app.safedriver.driver.Driver;
import com.utc.app.safedriver.evaluate.Evaluate;
import com.utc.app.safedriver.trip.status.StatusE;
import com.utc.app.safedriver.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_trip")
public class Trip {
    @Id
    @GeneratedValue
    private Long id;

    private Long date_of_hire;
    private Long time_start;
    private Long time_end;

    @Column(columnDefinition = "nvarchar(255)")
    private String pick_up_location;
    @Column(columnDefinition = "nvarchar(255)")
    private String drop_off_location;
    private Double fee;
    private String license_plate;
    private String car_name;
    private String vehicle_type;
    private String range_of_vehicle;
    @Column(columnDefinition = "nvarchar(255)")
    private String note;
    @Enumerated(EnumType.STRING)
    private StatusE status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_driver")
    private Driver driver;

    @OneToMany(mappedBy = "trip")
    private List<Evaluate> evaluates;
}
