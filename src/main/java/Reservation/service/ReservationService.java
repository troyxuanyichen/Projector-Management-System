package Reservation.service;

import Reservation.model.Projector;
import Reservation.model.Reservation;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public interface ReservationService {

    Optional<Long> insertReservation(Projector projector, Date date, Date start, Date end);

    Optional<Reservation> searchReservation(Long reservationId);

    Optional<String> deleteReservation(Long reservationId);

    Collection<Reservation> getAll();

    Collection<Reservation> searchByDate(Date date);
}
