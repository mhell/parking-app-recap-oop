package se.lexicon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.dao.CustomerDao;
import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.dao.ReservationDao;
import se.lexicon.dao.impl.CustomerDaoImpl;
import se.lexicon.dao.impl.ParkingSpotDaoImpl;
import se.lexicon.dao.impl.ReservationDaoImpl;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;
import se.lexicon.model.Status;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    CustomerDao customerDao = new CustomerDaoImpl();
    ReservationDao reservationDao = new ReservationDaoImpl();
    ParkingSpotDao parkingSpotDao = new ParkingSpotDaoImpl();
    ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(reservationDao, parkingSpotDao);
    }

    @Test
    void shouldReserveSuccessfully_whenCostumerAndSpotExists() {
        // Arrange
        Customer customer = new Customer("name", "123", "ABC");
        customerDao.create(customer);
        ParkingSpot parkingSpot = new ParkingSpot(1, 101, false);
        parkingSpotDao.create(parkingSpot);
        int duration = 1;
        // Act
        Reservation reservation = reservationService.reserveSpot(customer, duration, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        // Assert
        assertEquals(Status.ACTIVE, reservation.getStatus());
    }

    // parking spot taken
    @Test
    void shouldThrow_whenCustomerInvalid() {

    }

    // non existing customer

    // non existing spot

    // 0 or negative duration
}