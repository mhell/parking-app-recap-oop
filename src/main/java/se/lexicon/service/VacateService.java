package se.lexicon.service;

import se.lexicon.dao.ReservationDao;
import se.lexicon.model.Customer;

public class VacateService {
    ReservationDao reservationDao;

    public VacateService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public void vacateSpot(Customer customer) {
    }
}
