package se.lexicon.service;

import se.lexicon.dao.*;
import se.lexicon.dao.impl.*;
import se.lexicon.model.*;

import java.util.List;

public class ParkingService {
    private final ParkingSpotDao parkingSpotDao;

    public ParkingService(ParkingSpotDao parkingSpotDao) {
         this.parkingSpotDao = parkingSpotDao;
    }

    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotDao.findAll();
    }

}
