package se.lexicon.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotDaoImplTest {
    ParkingSpot parkingSpot=new ParkingSpot(1,101,false);
    ParkingSpotDao parkingSpotDao;

    @BeforeEach
    void setUp() {
        parkingSpotDao=new ParkingSpotDaoImpl();
    }

    @Test
    void create_NewParkingSpot() {
        ParkingSpot spot=parkingSpotDao.create(parkingSpot);
        assertEquals(parkingSpot,spot);
    }

    @Test
    void create_ExistingSpot_throwsRunTimeException() {
        parkingSpotDao.create(parkingSpot);
        Executable action=()-> parkingSpotDao.create(parkingSpot);
        assertThrows(RuntimeException.class,action,"The Parking Spot already exists");
    }

    @Test
    void findAll() {
        parkingSpotDao.create(parkingSpot);
        List<ParkingSpot> spots=parkingSpotDao.findAll();
        assertEquals(1,spots.size());
        assertEquals(parkingSpot,spots.get(0));
    }

    @Test
    void findBySpotNumber_IfExists_ReturnFound() {
        parkingSpotDao.create(parkingSpot);
        Optional<ParkingSpot> spotFound=parkingSpotDao.findBySpotNumber(parkingSpot.getSpotNumber());
        assertTrue(spotFound.isPresent());
    }

    @Test
    void findBySpotNumber_IfNotExists_ReturnFound() {
        Optional<ParkingSpot> spotFound=parkingSpotDao.findBySpotNumber(parkingSpot.getSpotNumber());
        assertFalse(spotFound.isPresent());
    }

    @Test
    void findAvailableSpots() {
        parkingSpotDao.create(parkingSpot);
        List<ParkingSpot> availableSpots=parkingSpotDao.findAvailableSpots();
        assertEquals(1,availableSpots.size());
        assertTrue(availableSpots.contains(parkingSpot));
    }

    @Test
    void update() {
        parkingSpotDao.create(parkingSpot);
        ParkingSpot updatedSpot=new ParkingSpot(1,101,true);
        parkingSpotDao.update(updatedSpot);
        assertTrue(parkingSpot.isOccupied());
    }
}