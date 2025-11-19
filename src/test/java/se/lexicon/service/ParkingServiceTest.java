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
    ParkingService service;
    @BeforeEach
    void setUp() {
        parkingSpotDao=new ParkingSpotDaoImpl();
        service=new ParkingService(parkingSpotDao);
    }

    @Test
    void getAllParkingSpots() {
        //Arrange
        ParkingSpot spot=new ParkingSpot(1,101,false);
        parkingSpotDao.create(spot);

        //Act
        List<ParkingSpot> parkingSpot= service.getAllParkingSpots();

        //Assert
        assertEquals(1,parkingSpot.size());
        //Also test a non existing SpotNumber
        Optional<ParkingSpot> emptyResult=parkingSpotDao.findBySpotNumber(99);
        assertTrue(emptyResult.isEmpty());

    }
}