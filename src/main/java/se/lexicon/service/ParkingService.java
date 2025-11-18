package se.lexicon.service;

import se.lexicon.dao.*;
import se.lexicon.dao.impl.*;
import se.lexicon.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ParkingService {
    ParkingSpotDao parkingSpotDao = new ParkingSpotDaoImpl();
    List<ParkingSpot> parkingSpots=new LinkedList<>();
    ParkingSpot spot1=new ParkingSpot(1,101,false);
    ParkingSpot spot2=new ParkingSpot(2,102,false);


    public List<ParkingSpot> getAllParkingSpots() {
        parkingSpotDao.create(spot1);
        parkingSpotDao.create(spot2);
        return parkingSpotDao.findAll();
    }

}
