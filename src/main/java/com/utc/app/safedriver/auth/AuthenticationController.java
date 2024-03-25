package com.utc.app.safedriver.auth;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utc.app.safedriver.common.Response;
import com.utc.app.safedriver.email.EmailAlreadyExistedException;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<Response<Object>> register(
            @RequestBody RegisterRequest request) {

        Response<Object> response;
        try {
            response = Response.builder()
                    .status(HttpStatus.OK)
                    .message("register successful")
                    .data(service.register(request))
                    .build();
        } catch (EmailAlreadyExistedException e) {
            response = Response.builder()
                    .status(HttpStatus.CONFLICT)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        } catch (OtpAuthenticationException e) {
            response = Response.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
        return new ResponseEntity<Response<Object>>(response, response.getStatusCode());
    }

    @PostMapping("/request-otp")
    public ResponseEntity<Response<Object>> requestOtp(@RequestBody RequestOtp request) {
        Response<Object> response;
        try {
            service.sendOtp(request);
            response = Response.builder()
                    .status(HttpStatus.OK)
                    .message("OTP is being sent to you, please check the spam emails if you didn't receive it")
                    .data(null)
                    .build();
        } catch (UnsupportedEncodingException | MessagingException e) {
            response = Response.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
        return new ResponseEntity<Response<Object>>(response, response.getStatusCode());
    }
}
