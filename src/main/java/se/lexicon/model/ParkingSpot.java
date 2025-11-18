package se.lexicon.model;

import java.util.Objects;

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

    private int spotNumber;
    private int areaCode;
    private boolean occupied;

    //Add constructors

    public ParkingSpot(int spotNumber, int areaCode, boolean occupied) {
        this.spotNumber = spotNumber;
        this.areaCode = areaCode;
        this.occupied = occupied;
    }


    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotNumber=" + spotNumber +
                ", areaCode=" + areaCode +
                ", occupied=" + occupied +
                '}';
    }

    /**
     * Marks the parking spot as occupied.
     * This method sets the `occupied` field to true.
     */
    public void occupy() {
        if(!occupied){
            occupied=true;
        }
    }

    /**
     * Marks the parking spot as vacant.
     * This method sets the `occupied` field to false.
     */
    public void vacate() {
        if(occupied){
            occupied=false;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpot that = (ParkingSpot) o;
        return Objects.equals(spotNumber, that.spotNumber) && Objects.equals(areaCode, that.areaCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotNumber, areaCode);
    }
}
