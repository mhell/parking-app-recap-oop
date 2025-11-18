package se.lexicon.service;

import se.lexicon.dao.*;
import se.lexicon.dao.impl.*;
import se.lexicon.model.*;

import java.util.List;
import java.util.Optional;

public class ParkingService {
    ParkingSpotDao parkingSpotDao = new ParkingSpotDaoImpl();

    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotDao.findAll();
    }

}
