package se.lexicon.service;

import se.lexicon.dao.*;
import se.lexicon.model.*;

import java.util.NoSuchElementException;

public class ReservationService {
    CustomerDao customerDao;
    ReservationDao reservationDao;
    ParkingSpotDao parkingSpotDao;

    public ReservationService(CustomerDao customerDao, ReservationDao reservationDao, ParkingSpotDao parkingSpotDao) {
        this.customerDao = customerDao;
        this.reservationDao = reservationDao;
        this.parkingSpotDao = parkingSpotDao;
    }

    public Reservation reserveSpot(int customerId, int duration, int areaCode, Integer spotNumber) {
        // Check if customer exists
        Customer customer = customerDao.findById(customerId).orElse(null);
        if (customer == null) {
            throw new NoSuchElementException("Customer doesn't exist");
        }
        // check if duration is > 0
        if (duration < 1) {
            throw new IllegalArgumentException("Duration must be a positive value");
        }
        // check if customer already has a parking lot reserved
        if (reservationDao.findAll().stream().anyMatch( reservation ->
                reservation.getCustomer().equals(customer))) {
            throw new IllegalArgumentException("Costumer has an existing reservation.");
        }
        // check if parking spot is available (
        ParkingSpot parkingSpot = parkingSpotDao.findAvailableSpots().stream().filter(spot ->
                spot.getSpotNumber() == spotNumber && spot.getAreaCode() == areaCode).findFirst().orElse(null);
        if (parkingSpot == null) {
            throw new IllegalArgumentException("Parking spot is not available");
        }
        parkingSpot.setOccupied(true);
        return reservationDao.create(new Reservation(duration, Status.ACTIVE, parkingSpot, customer));
    }
}
