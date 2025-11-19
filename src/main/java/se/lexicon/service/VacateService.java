package se.lexicon.service;

import se.lexicon.dao.ReservationDao;
import se.lexicon.model.Reservation;

public class VacateService {
    ReservationDao reservationDao;

    public VacateService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public Reservation vacateSpot(int areaCode, int spotNumber) {
        // find reservation with area code and spot number
        Reservation reservation = reservationDao.findAll().stream().filter( res ->
                res.getParkingSpot().getAreaCode() == areaCode && res.getParkingSpot().getSpotNumber() == spotNumber).
                findFirst().orElse(null);

        if (reservation == null) {
            throw new IllegalArgumentException("Parking spot does not exist.");
        } else if (!reservation.getParkingSpot().isOccupied()) {
            throw new IllegalArgumentException("Parking spot is not occupied.");
        }

        // set associated parking spot to unoccupied
        reservation.getParkingSpot().setOccupied(false);

        // update reservation to status COMPLETED
        reservation.complete();

        return reservation;
    }
}
