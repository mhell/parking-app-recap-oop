package se.lexicon.dao;

import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Optional;


public interface ParkingSpotDao {

    ParkingSpot create(ParkingSpot parkingSpot);

    List<ParkingSpot> findAll();

    Optional<ParkingSpot> findBySpotNumber(int spotNumber);

    List<ParkingSpot> findAvailableSpots();

    void update(ParkingSpot parkingSpot);

}
