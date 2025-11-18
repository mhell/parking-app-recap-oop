package se.lexicon.service;

import se.lexicon.dao.*;
import se.lexicon.model.*;

import java.time.LocalDateTime;

public class ReservationService {
    ReservationDao reservationDao;
    ParkingSpotDao parkingSpotDao;

    public ReservationService(ReservationDao reservationDao, ParkingSpotDao parkingSpotDao) {
        this.reservationDao = reservationDao;
        this.parkingSpotDao = parkingSpotDao;
    }

    public ParkingSpot reserveSpot(Customer customer, LocalDateTime startTime, LocalDateTime endTime, Integer areaCode, Integer spotNumber) {
        // check if parking spot is available
        if (parkingSpotDao.findBySpotNumber(spotNumber).isPresent()) {
            throw new IllegalArgumentException("Parking spot taken");
        }

        // check if customer already has a parking lot reserved
        if (reservationDao.findAll().stream().anyMatch( reservation ->  reservation.getCustomer().equals(customer))) {

        }


        return null;
    }
}
