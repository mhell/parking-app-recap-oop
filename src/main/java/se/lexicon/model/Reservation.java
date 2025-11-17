package se.lexicon.model;

import java.time.LocalDateTime;

/**
 * Represents a reservation made by a customer for a specific parking spot.
 * <p>
 * A reservation records which customer reserved which parking spot,
 * along with the start and end time of the reservation and its current status.
 */
public class Reservation {

    private String reservationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private ParkingSpot parkingSpot;
    private Customer customer;

    // TODO: Add constructors

    // TODO: Add getters and setters

    // TODO: Add toString() if needed

    /**
     * Marks the reservation as completed.
     * Changes status from ACTIVE to COMPLETED.
     */
    public void complete() {
        // TODO: Implement by setting status to COMPLETED
    }

    /**
     * Sets the end time of the reservation based on a duration.
     *
     * @param hours Number of hours for the reservation
     */
    public void setEndTimeByHours(int hours) {
        // TODO: Implement by adding hours to startTime
    }
}
