package se.lexicon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    CustomerDao customerDao = new CustomerDaoImpl();
    ReservationDao reservationDao = new ReservationDaoImpl();
    ParkingSpotDao parkingSpotDao = new ParkingSpotDaoImpl();
    ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(customerDao, reservationDao, parkingSpotDao);
    }

    @Test
    void shouldReserveSuccessfully_whenCostumerAndSpotExists() {
        // Arrange
        Customer customer = new Customer("name", "123", "ABC");
        customerDao.create(customer);
        ParkingSpot parkingSpot = new ParkingSpot(1, 101, false);
        parkingSpotDao.create(parkingSpot);
        // Act
        Reservation reservation = reservationService.reserveSpot(customer.getId(), 1, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        // Assert
        assertEquals(Status.ACTIVE, reservation.getStatus());
    }

    // parking spot taken
    @Test
    void shouldThrow_whenSpotTaken() {
        // Arrange
        ParkingSpot parkingSpot = new ParkingSpot(1, 101, false);
        parkingSpotDao.create(parkingSpot);
        Customer customer1 = new Customer("name", "123", "ABC");
        customerDao.create(customer1);
        Customer customer2 = new Customer("name2", "456", "DEF");
        customerDao.create(customer2);
        reservationService.reserveSpot(customer1.getId(), 1, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        // Act
        Executable action = () -> reservationService.reserveSpot(customer2.getId(), 1, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        // Assert
        assertThrows(IllegalArgumentException.class, action, "Should throw when parking spot taken.");
    }

    // non-existing customer
    @Test
    void shouldThrow_whenCustomerInvalid() {
        // Arrange
        Customer customer = new Customer("name", "123", "ABC");
        ParkingSpot parkingSpot = new ParkingSpot(1, 101, false);
        parkingSpotDao.create(parkingSpot);
        // Act
        Executable action = () -> reservationService.reserveSpot(customer.getId(), 1, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        // Assert
        assertThrows(NoSuchElementException.class, action, "Should throw when customer don't exist.");
    }

    // customer already made reservation
    @Test
    void shouldThrow_whenCustomerHaveActiveReservation() {
        // Arrange
        ParkingSpot parkingSpot = new ParkingSpot(1, 101, false);
        parkingSpotDao.create(parkingSpot);
        Customer customer = new Customer("name", "123", "ABC");
        customerDao.create(customer);
        reservationService.reserveSpot(customer.getId(), 1, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        // Act
        Executable action = () -> reservationService.reserveSpot(customer.getId(), 1, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        // Assert
        assertThrows(IllegalArgumentException.class, action, "Should throw when customer have an active reservation.");
    }

    // non-existing parking spot
    @Test
    void shouldThrow_whenNonExistingSpot() {
        // Arrange
        ParkingSpot parkingSpot = new ParkingSpot(1, 101, false);
        Customer customer = new Customer("name", "123", "ABC");
        customerDao.create(customer);
        // Act
        Executable action = () -> reservationService.reserveSpot(customer.getId(), 1, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        // Assert
        assertThrows(IllegalArgumentException.class, action, "Should throw when parking spot don't exist.");
    }

    // 0 or negative duration
    @Test
    void shouldThrow_whenNonPositiveDuration() {
        // Arrange
        Customer customer = new Customer("name", "123", "ABC");
        customerDao.create(customer);
        ParkingSpot parkingSpot = new ParkingSpot(1, 101, false);
        parkingSpotDao.create(parkingSpot);
        int duration = 0;
        // Act
        Executable action = () -> reservationService.reserveSpot(customer.getId(), duration, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        // Assert
        assertThrows(IllegalArgumentException.class, action, "Should throw when duration < 1.");
    }

    @Test
    void shouldReserveSuccessfully_whenCostumerVacatePrevious() {
        // Arrange
        Customer customer = new Customer("name", "123", "ABC");
        customerDao.create(customer);
        ParkingSpot parkingSpot = new ParkingSpot(1, 101, false);
        parkingSpotDao.create(parkingSpot);
        reservationService.reserveSpot(customer.getId(), 1, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        VacateService vacateService = new VacateService(reservationDao);
        // Act
        vacateService.vacateSpot(parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        Reservation reservation = reservationService.reserveSpot(customer.getId(), 1, parkingSpot.getAreaCode(), parkingSpot.getSpotNumber());
        // Assert
        assertEquals(Status.ACTIVE, reservation.getStatus());
    }
}