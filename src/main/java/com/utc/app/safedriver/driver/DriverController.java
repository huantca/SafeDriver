package com.utc.app.safedriver.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utc.app.safedriver.base.Response;
import com.utc.app.safedriver.exceptions.AuthenticationException;
import com.utc.app.safedriver.exceptions.DriverNotExistedException;
import com.utc.app.safedriver.exceptions.UserNotExistedException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService service;

    @PostMapping("login")
    public ResponseEntity<Response<Object>> login(@RequestBody Driver driver) {
        Response<Object> response = null;
        try {
            response = Response.builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(service.login(driver))
                    .build();
        } catch (UserNotExistedException e) {
            response = Response.builder()
                    .status(HttpStatus.CONFLICT.value())
                    .error(e.getMessage())
                    .data(null)
                    .build();
        } catch (AuthenticationException e) {
            response = Response.builder()
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .error(e.getMessage())
                    .data(null)
                    .build();
        }
        return new ResponseEntity<Response<Object>>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    @PostMapping("register")
    public ResponseEntity<Response<Object>> register(@RequestBody Driver driver) {
        Response<Object> response = null;
        try {
            response = Response.builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(service.register(driver))
                    .build();
        } catch (DriverNotExistedException e) {
            response = Response.builder()
                    .status(HttpStatus.CONFLICT.value())
                    .error(e.getMessage())
                    .data(null)
                    .build();
        }
        return new ResponseEntity<Response<Object>>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
}
