package com.utc.app.safedriver.evaluate;

import java.util.List;

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
@Entity(name = "_evaluate")
public class Evaluate {
    @Id
    @GeneratedValue
    private Long id;

    private Integer point;
    @Column(columnDefinition = "nvarchar(255)")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "id_trip")
    private Trip trip;

}