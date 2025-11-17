package se.lexicon.dao;

import se.lexicon.model.Reservation;

import java.util.Optional;

public interface ReservationDao {
    Reservation create(Reservation reservation);

    Optional<Reservation> findById(String reservationId);

    void update(Reservation reservation);
}
