package com.utc.app.safedriver.driver;

import java.util.List;

import com.utc.app.safedriver.license.License;
import com.utc.app.safedriver.relatives.Relatives;
import com.utc.app.safedriver.trip.Trip;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_driver")
public class Driver {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    private Integer age;
    private String email;
    private String password;

    @Column(columnDefinition = "nvarchar(255)")
    private String address;

    @OneToMany(mappedBy = "driver")
    private List<Trip> trips;

    @OneToMany(mappedBy = "driver")
    private List<License> license;

    @OneToMany(mappedBy = "driver")
    private List<Relatives> relatives;

    private String avatar;
    private Integer number_of_times_hired;
    private Integer id_number;
    private String id_card_front_photo;
    private String id_card_back_photo;
    private Integer driving_experience;
    private String activity_area;
    private Double service_prices;
    private String photo_of_health_certificate;
    private String health_type;
    private String resume_photo;
    private Long working_time;
    private Boolean isAccept;
    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private StatusDriver statusDriver;
}