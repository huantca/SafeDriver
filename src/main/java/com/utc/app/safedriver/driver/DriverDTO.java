package com.utc.app.safedriver.driver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;
    private StatusDriver statusDriver;

    public static DriverDTO fromUser(Driver driver) {
        return DriverDTO.builder()
                .id(driver.getId())
                .name(driver.getName())
                .age(driver.getAge())
                .email(driver.getEmail())
                .address(driver.getAddress())
                .latitude(driver.getLatitude())
                .longitude(driver.getLongitude())
                .statusDriver(driver.getStatusDriver())
                .build();
    }
}
