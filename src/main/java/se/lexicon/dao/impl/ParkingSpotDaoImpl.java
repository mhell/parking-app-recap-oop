package se.lexicon.dao.impl;

import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {
  private final List<ParkingSpot> parkingSpots=new LinkedList<>();

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        if(parkingSpots.contains(parkingSpot)){
            throw new RuntimeException("The Parking Spot is already available.");
        }
        parkingSpots.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public List<ParkingSpot> findAll() {
        return parkingSpots;
    }

    @Override
    public Optional<ParkingSpot> findBySpotNumber(int spotNumber) {
        for(ParkingSpot parkingSpot: parkingSpots){
            if(parkingSpot.getSpotNumber()==spotNumber){
                return Optional.of(parkingSpot);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ParkingSpot> findAvailableSpots() {
        List<ParkingSpot> availableSpot=new LinkedList<>();
        for(ParkingSpot spot:parkingSpots){
            if(!spot.isOccupied()){
                availableSpot.add(spot);
            }
        }
        return availableSpot;
    }

    @Override
    public void update(ParkingSpot parkingSpot) {
        for(ParkingSpot spot:parkingSpots){
            if(spot.getSpotNumber() == parkingSpot.getSpotNumber() && spot.getAreaCode()== parkingSpot.getAreaCode()){
                spot.setOccupied(parkingSpot.isOccupied());
                return;
            }
        }
    }
}
