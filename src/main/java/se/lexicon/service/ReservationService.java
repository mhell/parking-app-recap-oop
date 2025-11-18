package se.lexicon.service;

import se.lexicon.dao.*;
import se.lexicon.dao.impl.*;
import se.lexicon.model.*;

import java.time.LocalDateTime;

public class ReservationService {
    ReservationDao reservationDao = new ReservationDaoImpl();

    public void vacate(Customer customer) {
    }

    public ParkingSpot reserveSpot(Customer customer, LocalDateTime startTime, LocalDateTime endTime, Integer areaCode, Integer spotNumber) {
        return null;
    }
}
