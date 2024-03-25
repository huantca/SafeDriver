package com.utc.app.safedriver.license;

import java.util.List;

import com.utc.app.safedriver.driver.Driver;
import com.utc.app.safedriver.trip.Trip;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_license")
public class License {
    @Id
    @GeneratedValue
    private Long id;

    private String id_card_front_photo;
    private String id_card_back_photo;
    private String level_license;
    private Long date_range;
    private Long expiration_date;

    @Column(columnDefinition = "nvarchar(255)")
    private String issued_by;

    @ManyToOne
    @JoinColumn(name = "id_driver")
    private Driver driver;
}
