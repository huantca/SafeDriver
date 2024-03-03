package com.utc.app.safedriver.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utc.app.safedriver.base.Response;
import com.utc.app.safedriver.exceptions.AuthenticationException;
import com.utc.app.safedriver.exceptions.EmailAlreadyRegisteredException;
import com.utc.app.safedriver.exceptions.UserNotExistedException;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("login")
    public ResponseEntity<Response<Object>> login(@RequestBody User user) {
        Response<Object> response = null;
        try {
            response = Response.builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(service.login(user))
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
    public ResponseEntity<Response<Object>> register(@RequestBody User user) {
        Response<Object> response = null;
        try {
            response = Response.builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(service.register(user))
                    .build();
        } catch (EmailAlreadyRegisteredException e) {
            response = Response.builder()
                    .status(HttpStatus.CONFLICT.value())
                    .error(e.getMessage())
                    .data(null)
                    .build();
        }
        return new ResponseEntity<Response<Object>>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

}
