package com.utc.app.safedriver.trip;

import org.springframework.stereotype.Service;

import com.utc.app.safedriver.driver.Driver;
import com.utc.app.safedriver.driver.DriverRepository;
import com.utc.app.safedriver.driver.StatusDriver;
import com.utc.app.safedriver.email.EmailAlreadyExistedException;
import com.utc.app.safedriver.exceptions.DriverNotExistedException;
import com.utc.app.safedriver.exceptions.TripNotExistedException;
import com.utc.app.safedriver.exceptions.UserNotExistedException;
import com.utc.app.safedriver.trip.status.StatusE;
import com.utc.app.safedriver.user.User;
import com.utc.app.safedriver.user.UserDTO;
import com.utc.app.safedriver.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRespository respository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    public TripDTO requestTrip(Long idUser, Trip tripRequest) throws UserNotExistedException {
        if (idUser == null) {
            throw new UserNotExistedException("id not null");
        } else {
            User user = userRepository.findById(idUser).orElse(null);
            if (user == null) {
                throw new UserNotExistedException("user is not readdy");
            } else {
                Trip trip = Trip.builder()
                        .user(user)
                        .date_of_hire(tripRequest.getDate_of_hire())
                        .time_start(tripRequest.getTime_start())
                        .pick_up_location(tripRequest.getPick_up_location())
                        .drop_off_location(tripRequest.getDrop_off_location())
                        .fee(tripRequest.getFee())
                        .license_plate(tripRequest.getLicense_plate())
                        .car_name(tripRequest.getCar_name())
                        .range_of_vehicle(tripRequest.getRange_of_vehicle())
                        .vehicle_type(tripRequest.getVehicle_type())
                        .status(StatusE.WAIT)
                        .note(tripRequest.getNote())
                        .build();
                return TripDTO.fromTrip(respository.save(trip));
            }
        }

    }

    public TripDTO acceptTrip(Trip trip) throws DriverNotExistedException, TripNotExistedException {
        Driver driver = driverRepository.findById(trip.getDriver().getId()).orElse(null);
        Trip acceptTrip = respository.findById(trip.getId()).orElse(null);
        if (driver == null) {
            throw new DriverNotExistedException("Can't find the driver ");
        } else if (acceptTrip == null || acceptTrip.getStatus() == StatusE.CONFIRM
                || acceptTrip.getStatus() == StatusE.CANCEL) {
            throw new TripNotExistedException("trip is not ready");
        } else {
            // driver.setStatusDriver(StatusDriver.BUSY);
            acceptTrip.setDriver(driver);
            acceptTrip.setStatus(StatusE.CONFIRM);
            // driverRepository.save(driver);
        }
        return TripDTO.fromTrip(respository.save(acceptTrip));
    }

    public void completeTrip(Long idTrip) throws TripNotExistedException {
        Trip completeTrip = respository.findById(idTrip).orElse(null);
        if (completeTrip == null) {
            throw new TripNotExistedException("Can't find the trip");
        } else {
            completeTrip.setStatus(StatusE.COMPLETE);
        }

    }

    private void cancelTrip(Long idTrip) throws TripNotExistedException {
        Trip cancleTrip = respository.findById(idTrip).orElse(null);
        if (cancleTrip == null) {
            throw new TripNotExistedException("Can't find the trip");
        } else {
            cancleTrip.setStatus(StatusE.CANCEL);
        }

    }

}
