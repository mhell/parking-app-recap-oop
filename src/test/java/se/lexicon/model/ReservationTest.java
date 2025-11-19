package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {
    private Reservation reservation;
    private LocalDateTime start;
    private LocalDateTime end;
    private ParkingSpot parkingSpot;
    private Customer customer;

    @BeforeEach
    void setUp() {
        int duration = 2;
        reservation = new Reservation(duration, Status.ACTIVE, parkingSpot, customer);
    }

    @Test
    void constructor_shouldGenerateUUID() {
        assertNotNull(reservation);
        assertNotNull(reservation.toString());
        assertNotEquals("", reservation.toString());
    }
    @Test
    void getStartTime() {
        assertEquals(start, reservation.getStartTime());
    }
    @Test
    void setStartTime() {
        LocalDateTime newStart = start.plusHours(2);
        reservation.setStartTime(newStart);
        assertEquals(newStart, reservation.getStartTime());
    }


    @Test
    void getEndTime() {
        assertEquals(end, reservation.getEndTime());
    }

    @Test
    void setEndTime() {
        LocalDateTime newEnd = start.plusHours(5);
        reservation.setEndTime(newEnd);
        assertEquals(newEnd, reservation.getEndTime());
    }

    @Test
    void complete() {
        reservation.complete();
        assertEquals(Status.COMPLETED, reservation.getStatus());
    }

    @Test
    void setEndTimeByHours() {
        reservation.setEndTimeByHours(3);
        assertEquals(start.plusHours(3), reservation.getEndTime());
    }
}