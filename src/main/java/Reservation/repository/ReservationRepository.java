package Reservation.repository;

import Reservation.model.Reservation;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  Optional<Reservation> findById(Long id); //todo

  Collection<Reservation> findByProjectorId(Long id); //this one is fine

  Collection<Reservation> findByDate(Date date);
}
