package se.lexicon.service;

import se.lexicon.dao.*;
import se.lexicon.dao.impl.*;
import se.lexicon.model.*;

import java.util.List;

public class ParkingService {
    private final ParkingSpotDao parkingSpotDao;

    public ParkingService(ParkingSpotDao parkingSpotDao) {
        this.parkingSpotDao = parkingSpotDao;
        parkingSpotDao.create(new ParkingSpot(1,101,false));
        parkingSpotDao.create(new ParkingSpot(2,102,false));
        parkingSpotDao.create(new ParkingSpot(3,103,false));
    }

    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotDao.findAll();
    }

}
