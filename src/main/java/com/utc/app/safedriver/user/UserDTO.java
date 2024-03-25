package com.utc.app.safedriver.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;

    public static UserDTO fromUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .address(user.getAddress())
                .latitude(user.getLatitude())
                .longitude(user.getLongitude())
                .build();
    }
}
