package se.lexicon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.dao.impl.ParkingSpotDaoImpl;
import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ParkingServiceTest {

    ParkingSpotDao parkingSpotDao;
    @BeforeEach
    void setUp() {
        parkingSpotDao=new ParkingSpotDaoImpl();

    }

    @Test
    void shouldGetAllParkingSpots() {
        //Arrange
       ParkingSpot parkingSpot=new ParkingSpot(1,101,false);
       parkingSpotDao.create(parkingSpot);

       //Act
        List<ParkingSpot> spots= parkingSpotDao.findAll();

        //Assert
        assertEquals(1, spots.size());

        //Also test a nonexisting SpotNumber
        Optional<ParkingSpot> emptyResult=parkingSpotDao.findBySpotNumber(2);
        assertTrue(emptyResult.isEmpty());
    }
}