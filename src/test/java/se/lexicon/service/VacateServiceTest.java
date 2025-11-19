package se.lexicon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.dao.ReservationDao;
import se.lexicon.dao.impl.ReservationDaoImpl;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;
import se.lexicon.model.Status;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VacateServiceTest {
    ReservationDao reservationDao;
    VacateService vacateService;

    @BeforeEach
    void setUp() {
        reservationDao = new ReservationDaoImpl();
        vacateService = new VacateService(reservationDao);
    }

    private Reservation createReservation(int area, int spot, boolean occupied, Status status) {
        Customer c = new Customer("John", "070-1111111", "ABC123");
        ParkingSpot ps = new ParkingSpot(spot, area, occupied);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(2);
        return new Reservation(start, end, status, ps, c);
    }
    @Test
    void vacateSpot_shouldMarkSpotVacant_andCompleteReservation() {
        Reservation r = createReservation(10, 5, true, Status.ACTIVE);
        reservationDao.create(r);

        Reservation result = vacateService.vacateSpot(10, 5);

        assertFalse(result.getParkingSpot().isOccupied());
        assertEquals(Status.COMPLETED, result.getStatus());
    }
    @Test
    void vacateSpot_shouldThrow_whenSpotNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> vacateService.vacateSpot(99, 99));
    }

    @Test
    void vacateSpot_shouldThrow_whenSpotNotOccupied() {
        Reservation r = createReservation(20, 3, false, Status.ACTIVE);
        reservationDao.create(r);

        assertThrows(IlegalArgumentException.class,
                () -> vacateService.vacateSpot(20, 3));
    }
}
