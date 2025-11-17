package se.lexicon.dao.impl;
import se.lexicon.dao.ReservationDao;
import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {

    private final List<Reservation> reservations = new ArrayList<>();

    @Override
    public Optional<Reservation> findById(String reservationID) {
        return reservations.stream()
                .filter(r -> r.getReservationId().equals(reservationID))
                .findFirst();
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

}
