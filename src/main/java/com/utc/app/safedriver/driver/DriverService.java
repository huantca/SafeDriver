package com.utc.app.safedriver.driver;

import org.springframework.stereotype.Service;

import com.utc.app.safedriver.exceptions.AuthenticationException;
import com.utc.app.safedriver.exceptions.DriverNotExistedException;
import com.utc.app.safedriver.exceptions.UserNotExistedException;
import com.utc.app.safedriver.user.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository repository;

    private UserNotExistedException userNotExistedException = new UserNotExistedException(
            "user with this email not existed");

    DriverDTO login(Driver driver) throws UserNotExistedException, AuthenticationException {
        Driver savedDriver = repository.findByEmail(driver.getEmail()).orElseThrow(() -> userNotExistedException);
        if (savedDriver.getPassword().equals(driver.getPassword())) {
            return DriverDTO.fromUser(savedDriver);
        } else {
            throw new AuthenticationException("wrong password");
        }
    }

    DriverDTO register(Driver driver) throws DriverNotExistedException {
        if (driver.getId() != null) {
            Driver saveDriver = repository.findById(driver.getId()).orElse(null);
            if (saveDriver != null && saveDriver.getIsAccept() == false) {
                throw new DriverNotExistedException("Account is waiting for approval");
            } else if (saveDriver != null) {
                throw new DriverNotExistedException("Account already exists");
            } else {
                saveDriver = Driver.builder()
                        .name(driver.getName())
                        .age(driver.getAge())
                        .address(driver.getAddress())
                        .email(driver.getEmail())
                        .password(driver.getPassword())
                        .build();
                return DriverDTO.fromUser(repository.save(saveDriver));
            }
        } else {
            Driver saveDriver = Driver.builder()
                    .name(driver.getName())
                    .age(driver.getAge())
                    .address(driver.getAddress())
                    .email(driver.getEmail())
                    .password(driver.getPassword())
                    .build();
            return DriverDTO.fromUser(repository.save(saveDriver));
        }

    }
}
