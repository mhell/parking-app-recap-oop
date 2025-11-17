package se.lexicon.model;

/**
 * Represents a physical parking spot in the parking area.
 * <p>
 * Each parking spot is identified by a unique spot number and an area code.
 * The spot may either be available or currently occupied.
 * <p>
 * This class is used when displaying parking availability and when assigning a spot
 * to a customer's reservation.
 */
public class ParkingSpot {

    private Integer spotNumber;
    private Integer areaCode;
    private boolean occupied;

    // TODO: Add constructors

    // TODO: Add getters and setters

    // TODO: Add toString() if needed

    /**
     * Marks the parking spot as occupied.
     * This method sets the `occupied` field to true.
     */
    public void occupy() {
        // TODO: Implement by setting occupied to true
    }

    /**
     * Marks the parking spot as vacant.
     * This method sets the `occupied` field to false.
     */
    public void vacate() {
        // TODO: Implement by setting occupied to false
    }
}
