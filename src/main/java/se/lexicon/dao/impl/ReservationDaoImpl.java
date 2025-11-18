package se.lexicon.dao.impl;
import se.lexicon.dao.ReservationDao;
import se.lexicon.model.Reservation;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {

    private final List<Reservation> reservations = new LinkedList<>();

    @Override
    public Optional<Reservation> findById(String reservationID) {
        return reservations.stream()
                .filter(r -> r.getReservationId().equals(reservationID))
                .findFirst();
    }

    @Override
    public Reservation create(Reservation reservation) {
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public void update(Reservation reservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId().equals(reservation.getReservationId())) {
                reservations.set(i, reservation);
                return;
            }
        }
        throw new IllegalArgumentException("Reservation not found: " + reservation.getReservationId());
    }

    @Override
    public List<Reservation> findAll() {
        return reservations;
    }

}
