package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotTest {


    @Test
    void occupy() {
        ParkingSpot parkingSpot= new ParkingSpot(1,101,false);
        parkingSpot.occupy();
        assertTrue(parkingSpot.isOccupied(),"The spot has been occupied!");
    }

    @Test
    void vacate() {
        ParkingSpot parkingSpot= new ParkingSpot(1,101,false);
        parkingSpot.vacate();
        assertFalse(parkingSpot.isOccupied(),"The Parking spot is vacant.");
    }
}