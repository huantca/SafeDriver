package com.utc.app.safedriver.trip;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utc.app.safedriver.base.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService service;

    @PostMapping("requestTrip")
    public ResponseEntity<Response<Object>> requestTrip(@RequestParam Long idUser,
            @RequestBody Trip trip) {
        Response<Object> response = null;
        try {
            response = Response.builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(service.requestTrip(idUser, trip))
                    .build();
        } catch (Exception e) {
            response = Response.builder()
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .error(e.getMessage())
                    .data(null)
                    .build();
        }
        return new ResponseEntity<Response<Object>>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

}
