package se.lexicon.service;

import se.lexicon.dao.*;
import se.lexicon.model.*;

public class ReservationService {
    ReservationDao reservationDao;
    ParkingSpotDao parkingSpotDao;

    public ReservationService(ReservationDao reservationDao, ParkingSpotDao parkingSpotDao) {
        this.reservationDao = reservationDao;
        this.parkingSpotDao = parkingSpotDao;
    }

    public Reservation reserveSpot(Customer customer, int duration, int areaCode, Integer spotNumber) {
        // check if parking spot is available (
        ParkingSpot parkingSpot = parkingSpotDao.findAvailableSpots().stream().filter(spot ->
                spot.getSpotNumber() == spotNumber && spot.getAreaCode() == areaCode).findFirst().orElse(null);
        if (parkingSpot == null) {
            throw new IllegalArgumentException("Parking spot is not available");
        }

        // check if customer already has a parking lot reserved
        if (reservationDao.findAll().stream().anyMatch( reservation ->
                reservation.getCustomer().equals(customer))) {
            throw new IllegalArgumentException("Costumer has an existing reservation.");
        }

        return reservationDao.create(new Reservation(duration, Status.ACTIVE, parkingSpot, customer));
    }
}
