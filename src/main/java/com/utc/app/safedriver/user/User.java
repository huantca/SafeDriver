package com.utc.app.safedriver.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utc.app.safedriver.trip.Trip;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity(name = "_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    private Integer age;
    private String email;
    private String password;
    private String phone;

    @Column(columnDefinition = "nvarchar(255)")
    private String address;

    private String avatar;
    private Integer number_of_rentals;

    @OneToMany(mappedBy = "user")
    private List<Trip> trips;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String otp;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long otpRequestedTime;
    private Long createdTime;
    private Long lastModified;
    private Double latitude;
    private Double longitude;
}
