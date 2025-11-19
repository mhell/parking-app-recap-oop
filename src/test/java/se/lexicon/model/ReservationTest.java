package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {
    private Reservation reservation;
    private Customer customer;
    private ParkingSpot parkingSpot;
    private int durationHours = 3;
    @BeforeEach
    void setUp() {
        customer = new Customer("Test User", "0700000000", "ABC123");
        parkingSpot = new ParkingSpot(1, 10, false);

        reservation = new Reservation(durationHours, Status.ACTIVE, parkingSpot, customer);
    }

    @Test
    void constructor_shouldSetStartTime() {
        assertNotNull(reservation.getStartTime());
    }

    @Test
    void constructor_shouldSetEndTimeCorrectly() {
        LocalDateTime expected = reservation.getStartTime().plusHours(durationHours);
        assertEquals(expected, reservation.getEndTime());
    }

    @Test
    void constructor_shouldSetStatus() {
        assertEquals(Status.ACTIVE, reservation.getStatus());
    }

    @Test
    void constructor_shouldSetParkingSpot() {
        assertEquals(parkingSpot, reservation.getParkingSpot());
    }

    @Test
    void constructor_shouldSetCustomer() {
        assertEquals(customer, reservation.getCustomer());
    }

    @Test
    void constructor_shouldGenerateReservationId() {
        assertNotNull(reservation.getReservationId());
        assertFalse(reservation.getReservationId().isEmpty());
    }

    @Test
    void getEndTime() {
        assertEquals(reservation.getStartTime().plusHours(durationHours), reservation.getEndTime());
    }

    @Test
    void setEndTime() {
        LocalDateTime newEnd = reservation.getStartTime().plusHours(5);
        reservation.setEndTime(newEnd);
        assertEquals(newEnd, reservation.getEndTime());
    }

    @Test
    void complete_shouldChangeStatusToCompleted() {
        reservation.complete();
        assertEquals(Status.COMPLETED, reservation.getStatus());
    }

    @Test
    void setEndTimeByHours() {
        reservation.setEndTimeByHours(6);
        assertEquals(reservation.getStartTime().plusHours(6), reservation.getEndTime());
    }
}