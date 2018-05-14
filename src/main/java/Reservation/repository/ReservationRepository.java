package Reservation.repository;

import Reservation.model.Reservation;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  /**
   * Use the id of reservation to find the reservation.
   * @param id the id of reservation
   * @return the reservation correspond to the id if exists
   */
  Optional<Reservation> findById(Long id);

  /**
   * Find all the reservation of a projector.
   * @param id the id of the projector
   * @return list of reservation belongs to the projector
   */
  Collection<Reservation> findByProjectorId(Integer id); //this one is fine

  Collection<Reservation> findByDate(Date date);

  /**
   * Find all the reservation of a projector by projector id and date.
   * @param id the id of the projector
   * @param date the date te reservation happens
   * @return
   */
  Collection<Reservation> findByProjectorIdAndDate(Integer id, Date date);
}
