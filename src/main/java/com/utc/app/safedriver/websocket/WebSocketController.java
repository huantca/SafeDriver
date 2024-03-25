package com.utc.app.safedriver.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.utc.app.safedriver.constant.Constant;
import com.utc.app.safedriver.driver.Driver;
import com.utc.app.safedriver.driver.DriverRepository;
import com.utc.app.safedriver.driver.StatusDriver;
import com.utc.app.safedriver.exceptions.DriverNotExistedException;
import com.utc.app.safedriver.exceptions.TripNotExistedException;
import com.utc.app.safedriver.exceptions.UserNotExistedException;
import com.utc.app.safedriver.trip.Trip;
import com.utc.app.safedriver.trip.TripDTO;
import com.utc.app.safedriver.trip.TripRespository;
import com.utc.app.safedriver.trip.TripService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final TripService tripService;
    private final TripRespository respository;
    private final DriverRepository driverRepository;

    @Transactional
    @MessageMapping("/trip")
    TripDTO orderTrip(@RequestParam Trip trip) throws UserNotExistedException {
        TripDTO tripDTO = tripService.requestTrip(trip.getUser().getId(), trip);
        driverRepository.findAll().forEach(driver -> {
            if (driver.getStatusDriver() != StatusDriver.BUSY) {
                simpMessagingTemplate.convertAndSend("/topic/trip" + driver.getId(), tripDTO);
            }
        });
        return tripDTO;
    }

    @MessageMapping("/driver")
    TripDTO acceptTrip(@RequestParam Trip trip) throws DriverNotExistedException, TripNotExistedException {
        TripDTO tripDTO = tripService.acceptTrip(trip);
        simpMessagingTemplate.convertAndSend("/topic/driver/" + tripDTO.getUser().getId(), tripDTO);
        return tripDTO;
    }

    double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double lon1Rad = Math.toRadians(lon1);
        double lon2Rad = Math.toRadians(lon2);

        double x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
        double y = (lat2Rad - lat1Rad);
        double distance = Math.sqrt(x * x + y * y) * Constant.EARTH_RADIUS;

        return distance;
    }
}
