package se.lexicon.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.dao.ReservationDao;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;
import se.lexicon.model.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDaoImplTest {

    private ReservationDao testObject;

    @BeforeEach
    void setUp() {
        testObject = new ReservationDaoImpl();
    }

    // --- CREATE TEST ---
    @Test
    void create_shouldStoreReservationAndReturnSameInstance() {
        Reservation reservation = createSampleReservation();

        Reservation result = testObject.create(reservation);

        assertSame(reservation, result, "create() should return same instance");
        assertEquals(1, testObject.findAll().size(), "Should store 1 reservation");
        assertSame(reservation, testObject.findAll().get(0), "Stored instance should match");
    }

    // --- FIND BY ID TESTS ---
    @Test
    void findById_shouldReturnReservationWhenExists() {
        Reservation reservation = createSampleReservation();
        testObject.create(reservation);

        Optional<Reservation> result = testObject.findById(reservation.getReservationId());

        assertTrue(result.isPresent(), "Reservation should be found");
        assertEquals(reservation, result.get(), "Found reservation must match stored one");
    }

    @Test
    void findById_shouldReturnEmptyOptionalWhenMissing() {
        Optional<Reservation> result = testObject.findById("missing-id");

        assertTrue(result.isEmpty(), "Should return empty when reservation not found");
    }

    // --- FIND ALL ---
    @Test
    void findAll_shouldReturnAllReservations() {
        Reservation r1 = createSampleReservation();
        Reservation r2 = createAnotherReservation();

        testObject.create(r1);
        testObject.create(r2);

        List<Reservation> all = testObject.findAll();

        assertEquals(2, all.size(), "Should return 2 reservations");
        assertTrue(all.contains(r1), "List should contain reservation 1");
        assertTrue(all.contains(r2), "List should contain reservation 2");
    }

    // --- UPDATE TESTS ---
    @Test
    void update_shouldModifyExistingReservation() {
        Reservation reservation = createSampleReservation();
        testObject.create(reservation);

        // modify object directly (UUID stays same)
        reservation.complete();
        testObject.update(reservation);

        Reservation updated = testObject.findById(reservation.getReservationId()).get();
        assertEquals(Status.COMPLETED, updated.getStatus(), "Reservation status should be updated");
    }

    @Test
    void update_shouldThrowExceptionWhenNotFound() {
        Reservation notStored = createSampleReservation();

        assertThrows(IllegalArgumentException.class, () -> testObject.update(notStored), "Updating non-existing reservation should throw exception");
    }


    private Reservation createSampleReservation() {
        Customer customer = new Customer("John Doe", "070-1111111", "ABC123");
        ParkingSpot parkingSpot = new ParkingSpot(1, 99, false);
        int duration = 2;

        return new Reservation(2, Status.ACTIVE, parkingSpot, customer);
    }

    private Reservation createAnotherReservation() {
        Customer customer = new Customer("Jane Doe", "073-2222222", "XYZ789");
        ParkingSpot parkingSpot = new ParkingSpot(2, 88, false);
        int duration = 2;

        return new Reservation(duration, Status.ACTIVE, parkingSpot, customer);
    }
}
