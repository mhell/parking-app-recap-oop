package se.lexicon.service;

import se.lexicon.dao.ReservationDao;
import se.lexicon.model.Customer;

public class VacateService {
    ReservationDao reservationDao;

    public VacateService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public void vacateSpot(Customer customer) {
        // find reservation made by customer

        // set associated parking spot to unoccupied

        // update reservation to status COMPLETED
    }
}
