package com.utc.app.safedriver.relatives;

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
@Entity(name = "_relatives")
public class Relatives {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    private Integer phone;
    @Column(columnDefinition = "nvarchar(255)")
    private String address;

    @ManyToOne
    @JoinColumn(name = "id_driver")
    private Driver driver;
}